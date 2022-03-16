package cn.wyedward.core.aspect;

import cn.wyedward.core.annotation.Log;
import cn.wyedward.core.utils.HttpContextUtils;
import cn.wyedward.core.utils.IPUtils;
import cn.wyedward.core.entity.sys.SysLog;
import cn.wyedward.core.mapper.sys.SysLogMapper;
import cn.wyedward.core.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Pointcut("@annotation(cn.wyedward.core.annotation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        Object result = null;
        try {
            // 执行方法
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        // 执行时长(毫秒) (响应时间)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        //获取切入点方法结构
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点方法名
        Method method = signature.getMethod();
        //新建一个日志对象
        SysLog sysLog = new SysLog();
        //获取注解内容
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 设置操作内容为注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }
        //请求类名
        String className = joinPoint.getTarget().getClass().getName();
        //请求方法名
        String methodName = signature.getName();
        //设置请求方法内容为类名+方法名
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            //设置参数
            sysLog.setParams(params);
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 获取用户认证信息。
        String principal =(String) SecurityUtils.getSubject().getPrincipal();
        sysLog.setUserName(JwtUtil.getUsername(principal));
        //设置响应时间
        sysLog.setTime((int) time);
        //设置请求发起时间
        Date date = new Date();
        sysLog.setCreateTime(date);
        // 保存系统日志
        sysLogMapper.insert(sysLog);
    }
}
