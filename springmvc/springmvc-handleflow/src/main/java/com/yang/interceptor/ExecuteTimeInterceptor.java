package com.yang.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {
    // private static final Logger log = LoggerFactory.getLogger(ExecuteTimeInterceptor.class);

    /**
     * 在handler执行之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return super.preHandle(request, response, handler);
    }

    /**
     * 在handler执行之后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;

        System.out.println("[" + handler + "] executeTime : " + executeTime + "ms");
        //if (log.isDebugEnabled()) {
        //    log.debug("[" + handler + "] executeTime : " + executeTime + "ms");
        //}
        super.postHandle(request, response, handler, modelAndView);
    }
}
