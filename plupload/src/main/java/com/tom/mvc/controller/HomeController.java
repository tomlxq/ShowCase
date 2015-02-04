package com.tom.mvc.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.Callable;
/**
 * Created by Administrator on 15-2-4.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView view() {
        ModelAndView mv = new ModelAndView();
        //mv.addObject("user", user);
        mv.setViewName("index");
        return mv;
    }
}
