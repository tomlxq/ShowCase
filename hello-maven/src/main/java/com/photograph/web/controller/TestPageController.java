package com.photograph.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/1/4  11:19
 */
@Controller
@RequestMapping("/test")
public class TestPageController {
    @RequestMapping("/ajax")
    @ResponseBody
    public Object ajax(HttpServletRequest request){
        List<String> list=new ArrayList<String>();
        list.add("电视");
        list.add("洗衣机");
        list.add("冰箱");
        list.add("电脑");
        list.add("汽车");
        list.add("空调");
        list.add("自行车");
        list.add("饮水机");
        list.add("热水器");
        return list;
    }
}
