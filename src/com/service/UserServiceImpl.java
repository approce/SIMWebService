/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.dao.UserDAO;
import com.model.Role;
import com.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void createUser(User user) {
        //encrypt pass:
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        //user creates only with role "User":
        List<Role> roles = new LinkedList<Role>();
        roles.add(Role.getRole(Role.UserRole.USER));
        user.setRoles(roles);
        //set current date of registration:
        user.setCalendar(Calendar.getInstance());
        userDAO.saveUser(user);
    }


    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public boolean isUserName(String username) {
        return userDAO.isUserName(username);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getUser(username);
    }
}
