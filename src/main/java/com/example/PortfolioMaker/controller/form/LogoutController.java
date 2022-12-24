package com.example.PortfolioMaker.controller.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
@Controller
public class LogoutController {
    @Autowired
    HttpSession session;

    @GetMapping("/logout")
    protected ModelAndView logout(){
        ModelAndView mav = new ModelAndView();
        session.invalidate();
        return new ModelAndView("redirect:/login");
    }
}
