package com.example.PortfolioMaker.controller;

import com.example.PortfolioMaker.controller.form.UserForm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    HttpSession session;

//    @Autowired
//    UserService userService;

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView mav = new ModelAndView();
        //form用の空のentityを準備
        UserForm userForm = new UserForm();
        // 画面遷移先を指定
        mav.setViewName("/login");
        //準備した空のFormを保管
        mav.addObject("formModel", userForm);
        return mav;
    }




}
