package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Damrod on 02.06.2017.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private boolean isLoginedIn;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PlayableCharacter> character;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isLoginedIn() {
        return isLoginedIn;
    }

    public void setLoginedIn(boolean loginedIn) {
        isLoginedIn = loginedIn;
    }

    public List<PlayableCharacter> getCharacter() {
        return character;
    }

    public void setCharacter(List<PlayableCharacter> character) {
        this.character = character;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
