package com.yang.demo.config;

import com.yang.demo.interceptor.MyInterceptor;
import com.yang.demo.resolver.RequestPacketResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {
    public static final Logger log = LoggerFactory.getLogger(SpringWebConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //'/**'匹配所有
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }

    /**
     * 参数解析
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        log.info("addArgumentResolvers");
        resolvers.add(new RequestPacketResolver());
    }
}
