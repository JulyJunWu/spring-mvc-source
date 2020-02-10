package com.mvc.test.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 自定义需要导入的bean
 *
 * @author JunWu
 */
public class CustomSelector implements ImportSelector {

    /**
     * 获取将需要注册到IOC容器的bean的全路径名称
     *
     * @param annotationMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{TestConfig.class.getName(), TestConfig2.class.getName()};
    }

    public static class TestConfig {
    }

    public static class TestConfig2 {
        /**
         * @PostConstruct 等同于 指定initMethod
         */
        @PostConstruct
        public void initMethod() {
            System.out.println("@PostConstruct");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("@PreDestroy");
        }
    }

}
