package com.mvc.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 从配置文件加载的Listener
 *
 * @author JunWu
 */
@Slf4j
public class LoadListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info("从配置文件加载的Listener->{}", LoadListener.class.getName());
    }
}
