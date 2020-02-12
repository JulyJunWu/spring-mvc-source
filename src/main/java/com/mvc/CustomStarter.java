package com.mvc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 *
 *  注意:::
 *      此类是故意写在最外层的,为了避免让spring扫描,从而测试自定义starter
 * 自定义starter 原理
 *
 * @author JunWu
 * 关键点:
 * 1.spring.factories文件(类似Properties)
 * 2.配置org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.ws.xxxx类 ,配置类或者组件类或者@Imprt
 * 3.解析: 从AutoConfigurationExcludeFilter#getAutoConfigurations()获取自动注入扫描的key,也就是@EnableAutoConfiguration注解
 * 的全限定类名,根据此从spring.factories获取对应的value
 * 这边执行是依靠BeanFactoryPostProcessor的子接口BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry
 * 4.从spring.factories解析得到对应的value 是靠SpringFactoriesLoader类解析的,该类也是定义spring.factories所在路径
 */

@Component
public class CustomStarter implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("自动注入原理(starter)");
    }
}
