package com.mvc.test.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试循环引用
 * <p>
 * prototype(多实例)下的循环引用必定报错!
 * <p>
 * singleton(单例)下的双方都是通过构造注入必定报错
 *
 * @author JunWu
 */
@Configuration
public class CircularReference implements ApplicationContextAware {

    @Bean
    public CircularA circularA() {
        return new CircularA();
    }

    @Bean
    public CircularB circularB() {
        return new CircularB(circularA());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CircularA circularA = applicationContext.getBean(CircularA.class);
        CircularB circularB = applicationContext.getBean(CircularB.class);
        circularA.test();
        circularB.test();
    }

    public static class CircularA {
        @Autowired
        private CircularB circularB;

        public void test() {
            System.out.println(this.getClass().getSimpleName());
        }
    }

    public static class CircularB {

        private CircularA circularA;

        public CircularB(CircularA circularA) {
            this.circularA = circularA;
        }

        public void test() {
            System.out.println(this.getClass().getSimpleName());
        }
    }
}
