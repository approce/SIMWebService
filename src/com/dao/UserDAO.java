/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.User;

import java.util.List;

/**
 * @author Роман
 */
public interface UserDAO {

    void saveUser(User u);

    User getUser(String username);

    boolean isUserName(String username);



    List<User> getUsers();

}
