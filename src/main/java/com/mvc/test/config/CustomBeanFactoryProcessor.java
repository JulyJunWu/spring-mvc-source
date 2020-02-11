package com.mvc.test.config;

import com.mvc.test.service.TestService;
import com.mvc.test.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author JunWu
 * BeanFactoryPostProcessor
 * 执行时机:
 * 在bean实例化之前
 * 源码位置:
 * @see AbstractApplicationContext#refresh()
 * @see PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)
 * @see PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(Collection, ConfigurableListableBeanFactory)
 */
@Component
public class CustomBeanFactoryProcessor implements BeanFactoryPostProcessor, InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 注意事项::
     *      只有当使用Qualifier注解 进行注入的时候 忽略自定装配裁生效
     *      当使用 @Autowired 和 @Resource是无效的
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //忽略自动装配的类型
        configurableListableBeanFactory.ignoreDependencyInterface(TestService.class);
        configurableListableBeanFactory.ignoreDependencyInterface(UserService.class);
        //configurableListableBeanFactory.ignoreDependencyType(TestService.class);
        //configurableListableBeanFactory.ignoreDependencyType(UserService.class);
    }
}
