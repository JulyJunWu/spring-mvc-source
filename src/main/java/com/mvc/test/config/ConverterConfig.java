package com.mvc.test.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.web.format.WebConversionService;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 职责 : 注册 请求/响应 参数转换器
 *
 * @author JunWu
 */
@Component
@Conditional(value = ConverterConditional.class)
public class ConverterConfig implements InitializingBean {

    @Resource(name = "mvcConversionService")
    private WebConversionService webConversionService;

    @Override
    public void afterPropertiesSet() {
        register();
    }

    private void register() {
        if (Objects.nonNull(webConversionService)) {
            webConversionService.addConverter(new String2DateConverter());
        }
    }
}

/**
 * 字符串转日期
 */
class String2DateConverter implements Converter<String, Date> {
    private String format = "yyyy-MM-dd";

    @Override
    public Date convert(String s) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(this.format);
            Date date = format.parse(s);
            return date;
        } catch (Exception e) {
            return null;
        }
    }
}