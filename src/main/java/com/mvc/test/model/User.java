package com.mvc.test.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author JunWu
 * 用户实体
 */
@Data
public class User {

    private String name;
    private int age;
    /**
     * 自定义字段 序列化方式
     */
    @JsonSerialize(using = DateSerialize.class)
    private Date birthday;

    public static class DateSerialize extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String strTime = format.format(date);
            jsonGenerator.writeString(strTime);
        }
    }
}

