package com.mvc.test.advice;

import com.mvc.test.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

/**
 * @author JunWu
 * @ResponseBody 前置处理器
 * <p>
 * 类似的@RequestBody也是参照如此
 */
@ControllerAdvice
@Slf4j
public class UserResponseBodyAdvice implements ResponseBodyAdvice<User> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Type type = methodParameter.getGenericParameterType();
        boolean support = false;
        if (type instanceof Class) {
            support = User.class.isAssignableFrom((Class) type);
        }
        return support;
    }

    @Override
    public User beforeBodyWrite(User user, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        return user;
    }
}
