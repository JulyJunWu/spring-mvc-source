package com.mvc.test;

import com.mvc.test.config.CustomImportBeanDefinitionRegistrar;
import com.mvc.test.config.CustomSelector;
import com.mvc.test.model.Shop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * 启动类
 *
 * @author JunWu
 * @Import 注册组件 , 相当于@Bean
 */
@SpringBootApplication
@Import(value = {Shop.class, CustomSelector.class, CustomImportBeanDefinitionRegistrar.class})
public class SpringMvcApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(SpringMvcApplication.class, args);

        run.publishEvent(new Object());
    }
}
