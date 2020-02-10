package com.mvc.test.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 常量
 *
 * @author JunWu
 */
@Component
public class Constants {

    public static String URL;

    /**
     * @Value 注解与@Autowired同样是通过BeanPostProcessor接口实现类AutowiredAnnotationBeanPostProcessor进行注入的
     * @param url
     */
    @Value("${ws.url:无}")
    public void setURL(String url) {
        Constants.URL = url;
    }
}
