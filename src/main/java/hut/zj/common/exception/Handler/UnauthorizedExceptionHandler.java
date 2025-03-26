package hut.zj.common.exception.Handler;

import hut.zj.common.result.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class UnauthorizedExceptionHandler implements ExceptionHandler {
    public Object handler(HttpServletResponse response, Throwable e) {
        if(e instanceof UnauthorizedException){
            return Result.getError("你没有权限访问此页面!");
        }else{
            return null;
        }
    }
}
