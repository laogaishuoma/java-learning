package com.yang.demo.resolver;

import com.yang.demo.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaoguoqiang
 */
public class RequestPacketResolver implements HandlerMethodArgumentResolver {
    public static final Logger log = LoggerFactory.getLogger(RequestPacketResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        System.out.println("#supportsParameter: ");
        //如果函数包含我们的自定义注解，那就走resolveArgument()函数,可以支持多个注解
        return methodParameter.hasParameterAnnotation(Param.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest nwRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        log.info("参数解析器--HandlerMethodArgumentResolver");
        //获取参数名
        String parameterName = parameter.getParameterName();
        log.info("parameterName---" + parameterName);
        Class parameterType = parameter.getParameterType();
        Param param = parameter.getParameterAnnotation(Param.class);
        boolean required = param.required();
        //简单的案例：如果客户端未传值，就设置默认值
        Object res = nwRequest.getNativeRequest(HttpServletRequest.class).getParameter(parameterName);
        log.info("res---" + res);
        //判断参数是否存在
        if (required) {
            if (StringUtils.isEmpty(res)) {
                throw new MissingServletRequestParameterException(parameterName, parameterType.toString());
            }
        }
        //返回值就是controller接收得值
        return res;
    }
}
