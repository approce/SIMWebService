/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validation;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    private static final int USERNAME_MIN_LENGTH = 3;
    private static final int USERNAME_MAX_LENGTH = 20;
    private static final int emailMaxLength = 60;
    private static final int passwordMinLength = 6;
    private static final int passwordMaxLength = 20;
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]{0,}";
    private static final String emailPattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
    private static final String passwordPattern = "^[0-9a-zA-Z]{0,}";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public static boolean validateUsername(String username) {
        if (username == null || username.length() == 0) {
            return false;
        }
        if (username.length() < USERNAME_MIN_LENGTH || username.length() > USERNAME_MAX_LENGTH) {
            return false;
        }
        if (!username.matches(USERNAME_PATTERN)) {
            return false;
        }
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if ((user.getUsername() == null) || user.getUsername().length() > 20 || user.getUsername().length() < 3) {
            errors.rejectValue("username", "Size.username");
        }
        if ((user.getUsername() == null) || !user.getUsername().matches(USERNAME_PATTERN)) {
            errors.rejectValue("username", "Pattern.username");
        }
        if ((user.getEmail() == null) || user.getEmail().length() > 60) {
            errors.rejectValue("email", "Size.email");
        }
        if ((user.getEmail() == null) || !user.getEmail().matches(emailPattern)) {
            errors.rejectValue("email", "Pattern.email");
        }
        if ((user.getPassword() == null) || !user.getPassword().matches(passwordPattern)) {
            errors.rejectValue("password", "Pattern.password");
        }
        if ((user.getPassword() == null) || user.getPassword().length() < 6 || user.getUsername().length() > 20) {
            errors.rejectValue("password", "Size password");
        }
        if (userService.usernameExist(user.getUsername())) {
            errors.rejectValue("username", "Username.alreadyExist");
        }
    }

}
