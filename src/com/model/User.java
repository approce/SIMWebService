/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    public static enum UserRole {
        ROLE_USER,
        ROLE_ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "username")
    @NotEmpty
    private String username;  //TODO max 20

    @Column(name = "email")
    @NotEmpty
    private String email;                  //TODO max 60

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Request> requests;

    @Column(name = "balance")
    private float balance;

    @Column(name = "registered")
    private Calendar registered;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public Request getRequests(long id) {
        for (Request r : getRequests()) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Calendar getRegistered() {
        return registered;
    }

    public void setRegistered(Calendar registered) {
        this.registered = registered;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        LinkedList<GrantedAuthority> linkedList = new LinkedList<>();
        linkedList.add(new SimpleGrantedAuthority(role.toString()));
        return linkedList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
