package com.example.PortfolioMaker.service;

import com.example.PortfolioMaker.controller.form.TestForm;
import com.example.PortfolioMaker.controller.form.UserForm;
import com.example.PortfolioMaker.repository.UserRepository;

import com.example.PortfolioMaker.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void saveUser(UserForm userForm) {
        User user = setUserEntity(userForm);
        userRepository.save(user);
    }
    private User setUserEntity(UserForm userForm) {
        User user = new User();
        user.setId(userForm.getId());
        user.setName(userForm.getName());
        user.setUserName(userForm.getUserName());
        user.setMailAddress(userForm.getMailAddress());
        user.setPassword(userForm.getPassword());
        user.setColor(userForm.getColor());
        user.setDateOfBirth(userForm.getDateOfBirth());
        return user;
    }
}
