package com.mvc.test.controller;

import com.mvc.test.model.Shop;
import com.mvc.test.model.User;
import com.mvc.test.service.TestService;
import com.mvc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户controller
 *
 * @author JunWU
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     *  如何指定注入所需要的bean的名称
     *
     *  方式一 :
     *   使用@Resource(name="beanName")
     *  方式二:
     *   同时使用@Autowired @Qualifier("beanName")
     *
     *
     *  源码 @Autowired源码解析类 :
     *  @see AutowiredAnnotationBeanPostProcessor
     *
     *  源码 @Resource源码解析类:
     *    1.当name属性不为空时,根据 getBean(name,classType)获取
     *    2.为空时.默认使用字段名作为name,若容器中含有此beanName,则流程与1 一致;
     *    否则很复杂......最终没有的话话通过类型查找
     *    @see CommonAnnotationBeanPostProcessor#autowireResource
     */
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    // @Resource(name = "testService")
    @Resource
    private TestService testService;

    @ResponseBody
    @RequestMapping("/one")
    public User getUser() {
        User user = new User();
        user.setAge(20);
        user.setName("大宝");
        return user;
    }

    @RequestMapping("/shop")
    @ResponseBody
    public Shop getShop() {
        Shop shop = new Shop();
        shop.setPrice("9.99");
        shop.setShopName("牙刷");
        return shop;
    }

    @RequestMapping("/form")
    @ResponseBody
    public User form(User user) {
        return user;
    }
}


