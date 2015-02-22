/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Роман
 */
@Entity
@Table(name = "Role")
public class Role {

    public static enum UserRole {

        USER(1, "ROLE_USER"),
        ADMIN(2, "ROLE_ADMIN");

        private int id;
        private String name;

        UserRole(int id, String name) {
            this.id = id;
            this.name = name;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static Role getRole(UserRole role) {
        Role role1 = new Role();
        role1.setId(role.id);
        role1.setRole(role.name);
        return role1;
    }

}
