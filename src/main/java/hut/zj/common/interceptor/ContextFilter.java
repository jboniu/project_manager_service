package hut.zj.common.interceptor;

import hut.zj.common.context.ContextData;
import hut.zj.common.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
@Order(1)
@Component
public class ContextFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(ContextFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        logger.info("sessionId:" + req.getSession().getId());
        ContextData contextData = new ContextData();
        contextData.setRequest(req);
        rep.setHeader("Access-Control-Allow-Origin", "*");
        rep.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers",
                "Content-Type, x-requested-with, X-Custom-Header, Authorization");
        contextData.setResponse(rep);
        ContextUtils.setContexeData(contextData);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
