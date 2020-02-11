package com.mvc.test.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 使用注解注册观察者
 *
 * @EventListener 原理 :
 *     @See EventListenerMethodProcessor 实现了SmartInitializingSingleton接口,BeanFactoryPostProcessor等
 *     执行时机: 在bean实例化完毕,属性初始化完毕,执行afterSingletonsInstantiated函数,
 *     然后使用ApplicationListener的适配器包装@EveneListener注解修饰的method,并保存该beanName,
 *     最终保存到AbstractApplicationEventMulticaster.defaultRetriever.applicationListeners中
 *     当触发事件后执行调用反射执行方法
 *
 *     使用方式:
 *      1.实现ApplicationListener接口
 *      2.使用@EventListener注解
 * @author JunWu
 */
@Component
public class CustomListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 处理感兴趣事件
     * @param applicationEvent
     */
    @EventListener
    public void handleEvent(ApplicationEvent applicationEvent){
        System.out.println(applicationEvent);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println(applicationEvent);
    }
}
