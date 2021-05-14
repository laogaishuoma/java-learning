package com.yang.config;


import com.yang.annotation.UserId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class SpringWebConfig {

    /**
     * 参考链接: https://www.cnblogs.com/qinyuguan/p/12498868.html
     * 未生效???
     * @return
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            /**
             * 添加参数解析器
             * @param resolvers
             */
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                System.out.println("addArgumentResolvers------------");
                resolvers.add(new UserIdMethodArgumentResolver());
            }
        };
    }


    private class UserIdMethodArgumentResolver implements HandlerMethodArgumentResolver {
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
}
