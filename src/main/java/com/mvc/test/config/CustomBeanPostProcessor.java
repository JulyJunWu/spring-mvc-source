package com.mvc.test.config;

import com.mvc.test.controller.UserController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 自定义实现 BeanPostProcessor
 * <p>
 * 类似生命周期注解都是基于BeanPostProcessor接口实现的:
 * 如: @Autowired  -> AutowiredAnnotationBeanPostProcessor
 *
 * @author JunWu
 * @PostConstruct -> InitDestroyAnnotationBeanPostProcessor
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor, InitializingBean, DisposableBean, SmartInitializingSingleton {

    private String original = "original";

    @Override
    public void destroy() throws Exception {
        original = "destroy";
        System.out.println(original);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        original = "InitializingBean afterPropertiesSet";
        System.out.println(original);
    }

    @PostConstruct
    public void initMethod() {
        original = "initMethod";
        System.out.println(original);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserController) {
            System.out.println(UserController.class.getSimpleName());
        }
        return bean;
    }

    /**
     * 执行时机:
     * 实例化完毕,注入属性完毕后
     * 顺序 *Aware -> *BeanPostProcessor.before -> *InitializingBean -> initMethod -> *BeanPostProcessor.after ->
     * 最后 SmartInitializingSingleton.afterSingletonsInstantiated
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof UserController) {
            System.out.println(UserController.class.getSimpleName());
        }
        return bean;
    }

    @Override
    public void afterSingletonsInstantiated() {
        original = "SmartInitializingSingleton afterSingletonsInstantiated";
        System.out.println(original);
    }
}
