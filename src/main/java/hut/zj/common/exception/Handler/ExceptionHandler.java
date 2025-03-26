package hut.zj.common.exception.Handler;

import javax.servlet.http.HttpServletResponse;

public interface ExceptionHandler {

    public Object handler(HttpServletResponse response, Throwable e);


}
