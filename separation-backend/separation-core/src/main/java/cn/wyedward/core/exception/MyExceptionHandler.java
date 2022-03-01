package cn.wyedward.core.exception;

import cn.wyedward.core.common.ResponseBo;
import cn.wyedward.core.exception.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * MyExceptionHandler
 * 统一异常处理器
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public ResponseBo handleMyException(MyException e){
        ResponseBo responseBo=new ResponseBo();
        responseBo.put("code",e.getCode());
        responseBo.put("msg",e.getMsg());
        return responseBo;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseBo handlerNoFoundException(Exception e){
        log.error(e.getMessage(),e);
        return ResponseBo.exception(ErrorEnum.PATH_NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseBo handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(),e);
        return ResponseBo.exception(ErrorEnum.DUPLICATE_KEY);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseBo handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(),e);
        return ResponseBo.exception(ErrorEnum.NO_AUTH);
    }

    @ExceptionHandler(Exception.class)
    public ResponseBo handleException(Exception e){
        log.error(e.getMessage(),e);
        return ResponseBo.exception();
    }
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseBo handleUnauthenticatedException(UnauthenticatedException e){
        log.error(e.getMessage(),e);
        //System.out.println("token过期");
        return ResponseBo.exception(ErrorEnum.OVERDUE_TOKEN);
    }
}