package cn.wyedward.auth.filter;


import cn.wyedward.auth.jwt.JwtToken;
import cn.wyedward.core.utils.JwtUtil;
import cn.wyedward.core.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
       // System.out.println("---------------------");
        //System.out.println("isAccessAllowed：");
        //判断请求的请求头是否带上 "Token"
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                //token 错误
                responseError(response, e.getMessage());
            }
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
        return true;
    }

    /**
     * 判断用户是否想要登入。
     * 检测 header 里面是否包含 Token 字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        //System.out.println("isLoginAttempt：");
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Authorization");
        if (token != null&& !token.equals("") && token.length() != 0){
            return true;
        }
        return false;
    }

    /**
     * 创建shiro token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        //System.out.println("createToken：");
        String jwtToken = ((HttpServletRequest)request).getHeader("Authorization");
        //System.out.println("jwtToken："+jwtToken);
        if(jwtToken != null && !jwtToken.equals("") && jwtToken.length() != 0)
            return new JwtToken(jwtToken);
        return null;
    }

    /**
     * 系统默认的执行登陆操作
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        //System.out.println("executeLogin：");
        //根据header头中带的Authorization 中的token字符串转化为token对象
        AuthenticationToken token = this.createToken(request, response);
        //System.out.println(token);
        Subject subject = null;
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        } else {
            try {
                subject = this.getSubject(request, response);
               // System.out.println("shiroRealm进行身份认证：");
                subject.login(token);
                //System.out.println("shiroRealm进行身份认证成功：");
                return this.onLoginSuccess(token, subject, request, response);
            } catch (AuthenticationException var5) {
               // System.out.println("shiroRealm进行身份认证失败 尝试重新获取token登录");
                //刷新token
                //AccessToken 过期啦或者为null或第一次验证没有过  然后通过RefeshKey刷新一次AccessToken 再执行验证
                if(refreshToken(request,response)){
                   // System.out.println("token在刷新");
                    //重新判断身份
                    String newtoken;
                    //重新获取header的token
                    newtoken = ((HttpServletResponse)response).getHeader("Authorization");
                    //再执行新token的验证
                    subject.login(new JwtToken(newtoken));
                   // System.out.println("token刷新通过：");
                    return this.onLoginSuccess(new JwtToken(newtoken), subject, request, response);
                }else{
                    //System.out.println("token刷新不通过：");
                    //token过期啦
                    return this.onLoginFailure(token, var5, request, response);
                }
            }
        }
    }


    /**
     * shiro验证成功调用
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        //System.out.println("onLoginSuccess：");
        String Jwttoken = (String) token.getPrincipal();
        if (Jwttoken != null){
           // try{
                if(JwtUtil.verify(Jwttoken, JwtUtil.getUsername(Jwttoken))){
                    //System.out.println("进行token认证：");
                    //判断Redis是否存在所对应的RefreshToken
                    String username = JwtUtil.getUsername(Jwttoken);
                    Long currentTime=JwtUtil.getCurrentTime(Jwttoken);
                    if (RedisUtil.hasKey("freshKey:"+username)) {
                        Long currentTimeMillisRedis = (Long) RedisUtil.get("freshKey:"+username);
                        if (currentTimeMillisRedis.equals(currentTime)) {
                            //System.out.println("token认证通过：");
                            return true;
                        }
                    }
                }else{
                    //System.out.println("token认证不通过：");
                    return false;
                }

        }
        return true;
    }

    /**
     * 刷新AccessToken，进行判断RefreshToken是否过期，未过期就返回新的AccessToken且继续正常访问
     * @param request
     * @param response
     * @return
     */
    private boolean refreshToken(ServletRequest request, ServletResponse response) {
        System.out.println("refreshToken：");
        String token = ((HttpServletRequest)request).getHeader("Authorization");
        String username = JwtUtil.getUsername(token);
        Long currentTime=JwtUtil.getCurrentTime(token);
        //System.out.println("更新前:"+token);
        //System.out.println("更新前:"+currentTime);
        // 判断Redis中RefreshToken是否存在
        if (RedisUtil.hasKey("freshKey:"+username)) {
            //System.out.println("redisUtil：");
            // Redis中RefreshToken还存在，获取RefreshToken的时间戳
            Long currentTimeMillisRedis = (Long) RedisUtil.get("freshKey:"+username);
            // 获取当前AccessToken中的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，进行AccessToken刷新
            if (currentTimeMillisRedis.equals(currentTime)) {
                //System.out.println("currentTime：");
                // 获取当前最新时间戳
                Long currentTimeMillis =System.currentTimeMillis();
                RedisUtil.set("freshKey:"+username, currentTimeMillis,
                       JwtUtil.REFRESH_EXPIRE_TIME);
                //System.out.println("重新设置了redis freshkey值");
                // 刷新AccessToken，设置时间戳为当前最新时间戳
                token = JwtUtil.createToken(username, currentTimeMillis);
                //System.out.println("更新后:"+token);
                //System.out.println("更新后:"+currentTime);
                //刷新完毕后 更新请求头的authorization
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                //System.out.println("更新后的token");
                //设置header为新token
                httpServletResponse.setHeader("Authorization", token);
                //System.out.println("返回给前端的新凭证:"+httpServletResponse.getHeader("Authorization"));

                httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
                return true;
            }
        }
        return false;
    }


    /**
     * isAccessAllowed为false时调用，验证失败
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //System.out.println("onAccessDenied");
        this.sendChallenge(request,response);
        responseError(response,"token verify fail");
        return false;
    }





    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     */
    private void responseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/#/error/"+message);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}













