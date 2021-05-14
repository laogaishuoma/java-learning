package com.yang.config.resolver;

import com.yang.annotation.UserId;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserIdMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().equals(Integer.class) && parameter.hasParameterAnnotation(UserId.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mvController, NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
        UserId currentUserAnnotation = parameter.getParameterAnnotation(UserId.class);

        /**
        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication auth = sc.getAuthentication();
        LoginUser loginUser = (LoginUser) auth.getPrincipal();
        Integer userId = loginUser.getId();
        return userId;
        */

        return 111;
    }
}
