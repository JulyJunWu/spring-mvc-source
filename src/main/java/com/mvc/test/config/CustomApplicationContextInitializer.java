package com.mvc.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 自定义 ApplicationContextInitializer
 * @author JunWu
 *
 * 所有 从 spring.factories文件加载的ApplicationContextListener 会加入到 IOC 容器 中
 */
@Slf4j
public class CustomApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        log.info("自定义ApplicationContextInitializer[{}]",CustomApplicationContextInitializer.class.getName());
    }
}
