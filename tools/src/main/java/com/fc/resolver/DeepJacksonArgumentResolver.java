package com.fc.resolver;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import com.fc.annotations.JsonParam;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 统一参数处理,JsonParam注解
 *
 * @author fangcong on 2016/12/2.
 */
public class DeepJacksonArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JsonParam.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Type pType = parameter.getMethod().getGenericParameterTypes()[parameter.getParameterIndex()];
        String paramVal = webRequest.getParameter(parameter.getParameterName());
        if (paramVal == null) {
            throw new IllegalArgumentException("json接口参数为空或参数名称错误");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(format);
            return objectMapper.readValue(paramVal, TypeFactory.defaultInstance().constructType(pType));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
