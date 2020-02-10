package com.mvc.test;

import com.mvc.test.model.Shop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 启动类
 *
 * @Import 注册组件 , 相当于@Bean
 * @author JunWu
 */
@SpringBootApplication
@Import(value = {Shop.class})
public class SpringMvcApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringMvcApplication.class);

    }
}
