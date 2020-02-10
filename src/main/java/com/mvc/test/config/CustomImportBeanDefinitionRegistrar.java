package com.mvc.test.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 直接自己构建BeanDefinition注册bean(不推荐)
 *
 * @author JunWu
 */
public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 构建BeanDefinition
     *
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 构建描述bean的对象
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(TestBeanRegister.class)
                // 注入字段属性
                .addPropertyValue("detail", "Hello World")
                // 是否延迟加载
                .setLazyInit(false)
                // 作用域
                .setScope("singleton")
                .setInitMethodName("initMethod")
                .getBeanDefinition();
        // 注册
        beanDefinitionRegistry.registerBeanDefinition("testBeanRegister", beanDefinition);
    }

    @Getter
    @Setter
    public static class TestBeanRegister {
        private String detail;

        public void initMethod() {
            System.out.println("init-method " + detail);
        }
    }
}
