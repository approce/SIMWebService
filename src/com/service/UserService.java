/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Роман
 */

public interface UserService extends UserDetailsService {

    void createUser(User u);

    void saveUser(User u);

    boolean isUserName(String username);

}
