package com.tom.demo.controller;

import com.tom.demo.model.User;
import com.tom.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/2/3  21:27
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
     UserService userService;
    @RequestMapping("/{id}")
    public String showUser(@PathVariable Integer id, HttpServletRequest request) {

        User u = userService.getUserById(id);
        System.out.println(u);
        System.out.println(id);
        request.setAttribute("user", u);
        return "showUser";
    }
}
