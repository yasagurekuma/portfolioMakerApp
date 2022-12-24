package com.example.PortfolioMaker.service;

import com.example.PortfolioMaker.controller.form.UserForm;
import com.example.PortfolioMaker.repository.UserRepository;
import com.example.PortfolioMaker.repository.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String CheckUser(UserForm userForm)  {
        List<User> result = new ArrayList<>();

        result.add(userRepository.findByMailAddress(userForm.getMailAddress()));
        if (result.get(0) != null){
            return "重複";
        } else {
            return "重複なし";
        }
    }
    public void saveUser(UserForm userForm)  {

        User user = setUserEntity(userForm);
        userRepository.save(user);
    }
    private User setUserEntity(UserForm userForm)  {
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
    public UserForm editUser(Integer id)  {
        List<User> results = new ArrayList<>();
        results.add(userRepository.findById(id).orElse(null));
        if (results.get(0) == null) {
            return null;
        }
        UserForm user = setUserForm(results);
        return user;
    }

    private UserForm setUserForm(List<User> results)  {
        UserForm user = new UserForm();

            User result = results.get(0);
            user.setId(result.getId());
            user.setName(result.getName());
            user.setUserName(result.getUserName());
            user.setMailAddress(result.getMailAddress());
            user.setPassword(result.getPassword());
            user.setPassword(result.getPassword());
            user.setDateOfBirth(result.getDateOfBirth());
            user.setColor(result.getColor());

        return user;
    }
    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByMailAddressAndPassword(email, password);
        return user;
    }
}
