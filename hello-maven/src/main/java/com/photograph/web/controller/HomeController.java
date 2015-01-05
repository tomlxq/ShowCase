package com.photograph.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/4  21:40
 */
@Controller
public class HomeController {
    /**
     * 主页
     */
    @RequestMapping("/")
    public String home() {
        // 转到前台页面
        return "front/index";
    }
}
