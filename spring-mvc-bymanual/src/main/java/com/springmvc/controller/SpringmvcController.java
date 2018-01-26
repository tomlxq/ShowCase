package com.springmvc.controller;

/**
 * Created by tom on 2018/1/26.
 */

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        import com.springmvc.annotation.Autowired;
        import com.springmvc.annotation.Controller;
        import com.springmvc.annotation.RequestMapping;
        import com.springmvc.service.MyService1;
        import com.springmvc.service.MyService2;

@Controller
@RequestMapping("/demo")
public class SpringmvcController {
    @Autowired("MyService1Impl")
    MyService1 myService;
    @Autowired("MyService2Impl")
    MyService2 smService;

    @RequestMapping("/insert")
    public String insert(HttpServletRequest request, HttpServletResponse response, String param) {
        myService.insert(null);
        smService.insert(null);
        return null;
    }

    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, HttpServletResponse response, String param) {
        myService.delete(null);
        smService.delete(null);
        return null;
    }

    @RequestMapping("/update")
    public String update(HttpServletRequest request, HttpServletResponse response, String param) {
        myService.update(null);
        smService.update(null);
        return null;
    }

    @RequestMapping("/select")
    public String select(HttpServletRequest request, HttpServletResponse response, String param) {
        myService.select(null);
        smService.select(null);
        return null;
    }
}