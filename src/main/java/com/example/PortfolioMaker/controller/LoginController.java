package com.example.PortfolioMaker.controller;

import com.example.PortfolioMaker.controller.form.UserForm;
import com.example.PortfolioMaker.repository.entity.User;
import com.example.PortfolioMaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static com.example.PortfolioMaker.error.ErrorMessage.*;

@Controller
public class LoginController {
    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public ModelAndView loginView() {
        ModelAndView mav = new ModelAndView();
        //form用の空のentityを準備
        UserForm userForm = new UserForm();
        // 画面遷移先を指定
        mav.setViewName("/login");

        //エラーメッセージをセッションから取得してセット
        ArrayList<String> errorMessages = (ArrayList<String>) session.getAttribute("errorMessage");
        mav.addObject("errorMessages", errorMessages);

        //準備した空のFormを保管
        mav.addObject("formModel", userForm);
        return mav;
    }

    @PostMapping("/login")
    public ModelAndView login(@Validated @ModelAttribute("formModel") UserForm userForm,
                                                                    BindingResult errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("/login");
        }
        ModelAndView mav = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();

        // フォームに入力したaccountとpasswordを参照
        String email = userForm.getMailAddress();
        String password = userForm.getPassword();
        User user = userService.findByEmailAndPassword(email, password);
        //　ユーザー情報が空であればログイン画面に返す
        if (user == null) {
            mav.setViewName("/login");
            errorMessages.add(E1001);
            errorMessages.add(E1002);
            mav.addObject("errorMessages", errorMessages);
            return mav;
        }
        session.setAttribute("loginUser", user);
        session.setAttribute("userId", user.getId());
        // TOP画面に遷移
        return new ModelAndView("redirect:/");
    }



}
