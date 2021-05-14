package com.yang.demo.interceptor;

import com.yang.demo.annotation.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    public static final Logger log = LoggerFactory.getLogger(MyInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandle");
        //如果是SpringMVC请求
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            log.info("当前拦截的方法为：{}", handlerMethod.getMethod().getName());
            log.info("当前拦截的方法参数长度为：{}", handlerMethod.getMethod().getParameters().length);
            log.info("当前拦截的类为：{}", handlerMethod.getBean().getClass().getName());
            String uri = request.getRequestURI();
            log.info("拦截的uri：" + uri);
            //获取方法注解
            Login login = handlerMethod.getMethodAnnotation(Login.class);
            if (login != null) {
                log.info("login.required()---" + String.valueOf(login.required()));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}
