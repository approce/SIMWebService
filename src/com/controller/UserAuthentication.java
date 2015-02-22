/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.User;
import com.service.UserService;
import com.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserAuthentication {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    @Qualifier("authenticationManager")
    protected AuthenticationManager authenticationManager;

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String initSignUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "signUp";
        } else {
            userService.createUser(user);
            //forward to login page for auto-login:
            return "forward:security_check?" +
                    "username=" + user.getUsername()
                    + "&password=" + user.getPassword();
        }
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String initSignIn() {
        return "signIn";
    }


    @RequestMapping(value = "/signup/checkUserName", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> checkUsername(@RequestParam(value = "username") String username) {
        //create JSON body:
        Map<String, Object> json = new HashMap<String, Object>();
        //status code (defauld= fail).
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        //headers:
        HttpHeaders headers = new HttpHeaders();

        //0. Check if username matches pattern:
        boolean matches = UserValidator.validateUsername(username);
        if (!matches) {
            //if username validation fails:
            json.put("invalid", true);
        } else if (userService.isUserName(username)) {
            //1. Check if username already exist:
            //if exist:
            json.put("exist", true);
        } else {
            //if everything passed:
            statusCode = HttpStatus.OK;
        }
        return new ResponseEntity<Map<String, Object>>(json, headers, statusCode);
    }
}
