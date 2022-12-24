package com.example.PortfolioMaker.controller;

import com.example.PortfolioMaker.controller.form.TestForm;
import com.example.PortfolioMaker.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class SampleController {
    @Autowired
    TestService testService;
    @Autowired
    HttpSession session;

    @GetMapping
    public ModelAndView top() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/top");
        return mav;
    }

    @GetMapping("/test")
    public ModelAndView test(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("/test");
        TestForm testForm = new TestForm();
        mav.addObject("test", testForm);
        return mav;
    }

    @PostMapping("/addName")
    public ModelAndView addName(@ModelAttribute("test") TestForm testForm){

        testService.saveTest(testForm);
        return new ModelAndView("redirect:/");
    }

}

