package com.example.PortfolioMaker.controller;

import com.example.PortfolioMaker.controller.form.UserForm;
import com.example.PortfolioMaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import static java.lang.String.valueOf;

@Controller
public class UserController {

    @Autowired
    HttpSession session;
    @Autowired
    UserService userService;

    @GetMapping("/userAdd")
    public ModelAndView user() {

        ModelAndView mav = new ModelAndView();
        //form用の空のentityを準備
        UserForm userForm = new UserForm();
        // 画面遷移先を指定
        mav.setViewName("/userAdd");
        //準備した空のFormを保管
        mav.addObject("formModel", userForm);
        return mav;
    }

    @PostMapping("/newUser")
    public ModelAndView newUser(@ModelAttribute("formModel") UserForm userForm,
                                @RequestParam("confirmPassword")String confirmPassword,
                                @RequestParam(name = "birth", required = false) String birth) throws ParseException {

        ModelAndView mav = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay;
        birthDay = sdf.parse(birth);
        userForm.setDateOfBirth(birthDay);

        if (confirmPassword == null) {
            errorMessages.add("失敗");
            mav.addObject("errorMessages", errorMessages);
            mav.setViewName("/userAdd");
            return mav;
        }
        // 入力パスワードと確認用パスワードが一致している場合
        if (userForm.getPassword().equals(confirmPassword)) {
            // ユーザー情報をテーブルへ格納
            userService.saveUser(userForm);
        }
        // rootへリダイレクト
        return new ModelAndView("redirect:/login");
    }
    @GetMapping("/userEdit")
    public ModelAndView userEdit() throws ParseException, IllegalBlockSizeException, BadPaddingException {

        ModelAndView mav = new ModelAndView();
        //編集するユーザー（ログインしているユーザー）のIDを取得する。
        Integer userId = (Integer) session.getAttribute("userId");
//        Integer userId = 6;
        UserForm userForm = userService.editUser(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthDay = sdf.format(userForm.getDateOfBirth());
        userForm.setDateOfBirth(sdf.parse(birthDay));
        // 画面遷移先を指定
        mav.setViewName("/userEdit");
        //準備した空のFormを保管
        mav.addObject("birthDay", birthDay);
        mav.addObject("formModel", userForm);
        return mav;
    }
    @PostMapping("/editUser")
    public ModelAndView editUser(@ModelAttribute("formModel") UserForm userForm,
                                @RequestParam("confirmPassword")String confirmPassword,
                                @RequestParam(name = "birth", required = false) String birth) throws ParseException, IllegalBlockSizeException, BadPaddingException {

        userForm.setId((Integer)session.getAttribute("userId"));
        ModelAndView mav = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();

        UserForm account = userService.editUser((Integer)session.getAttribute("userId"));
        userForm.setDateOfBirth(account.getDateOfBirth());
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date birthDay;
//        birthDay = sdf.parse(birth);
//        userForm.setDateOfBirth(birthDay);

        if(userForm.getPassword() == "" && confirmPassword == ""){
            Integer userId = (Integer) session.getAttribute("userId");
//            Integer userId = 6;
            UserForm thisUser = userService.editUser(userId);
            userForm.setPassword(thisUser.getPassword());
        } else {
            if (userForm.getPassword().equals(confirmPassword)) {
            // ユーザー情報をテーブルへ格納
            userService.saveUser(userForm);
            } else {
                errorMessages.add("パスワードと確認パスワードが異なります。");
                mav.addObject("errorMessages", errorMessages);
                mav.setViewName("/userEdit");
                return mav;
            }
        }

        userService.saveUser(userForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }
}
