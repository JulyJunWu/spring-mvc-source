package com.mvc.test.controller;

import com.mvc.test.model.Shop;
import com.mvc.test.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户controller
 *
 * @author JunWU
 */
@Controller
@RequestMapping("/user")
public class UserController {

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


