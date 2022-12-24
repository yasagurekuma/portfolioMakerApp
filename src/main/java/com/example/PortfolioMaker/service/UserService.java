package com.example.PortfolioMaker.service;

import com.example.PortfolioMaker.controller.form.UserForm;
import com.example.PortfolioMaker.repository.UserRepository;
import com.example.PortfolioMaker.repository.entity.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
//    private Cipher decrypter;
    public void saveUser(UserForm userForm)  {
        //暗号化一時的になし
//        String encPassword = CipherUtil.encrypt(userForm.getPassword());
//        userForm.setPassword(encPassword);
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
    public UserForm editUser(Integer id) throws IllegalBlockSizeException, BadPaddingException {
        List<User> results = new ArrayList<>();
        results.add(userRepository.findById(id).orElse(null));
        if (results.get(0) == null) {
            return null;
        }
        List<UserForm> users = setUserForm(results);
        return users.get(0);
    }

    private List<UserForm> setUserForm(List<User> results) throws IllegalBlockSizeException, BadPaddingException {
        List<UserForm> users = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            UserForm user = new UserForm();
            User result = results.get(i);
            user.setId(result.getId());
            user.setName(result.getName());
            user.setUserName(result.getUserName());
            user.setMailAddress(result.getMailAddress());
//            byte[] str = Base64.getDecoder().decode(result.getPassword());     // 暗号文字列を元のバイナリに戻す
//            byte[] text = decrypter.doFinal(str);
            user.setPassword(result.getPassword());
            user.setPassword(result.getPassword());
            user.setDateOfBirth(result.getDateOfBirth());
            user.setColor(result.getColor());
            users.add(user);
        }
        return users;
    }
    public User findByEmailAndPassword(String email, String password) {
//        String encPassword = CipherUtil.encrypt(password);
        User user = userRepository.findByMailAddressAndPassword(email, password);
        return user;
    }
}
