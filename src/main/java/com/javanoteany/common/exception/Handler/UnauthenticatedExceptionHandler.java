package com.javanoteany.common.exception.Handler;

import com.javanoteany.common.result.Result;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class UnauthenticatedExceptionHandler implements ExceptionHandler {
    public Object handler(HttpServletResponse response, Throwable e) {
        if(e instanceof UnauthenticatedException){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return Result.getError("请先登录用户!");
        }else{
            return null;
        }
    }
}
