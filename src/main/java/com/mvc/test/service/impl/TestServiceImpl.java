package com.mvc.test.service.impl;

import com.mvc.test.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author JunWu
 * 测试服务实现类
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    public void say() {
        System.out.println("Hello World");
    }
}
