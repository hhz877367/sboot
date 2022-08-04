package com.baizhi.controller;

import com.baizhi.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component("/testController")
public class TestController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("TestController");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("zd",new Student());
        return modelAndView;
    }
}
