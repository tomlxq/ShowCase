/**
 * 说明：
 *
 * @author tom
 * @version 创建时间： 2015/5/14  7:19
 */
package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

    @RequestMapping(value={"/", "index"}, method=RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
