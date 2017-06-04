package com.example.demo.services;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Damrod on 02.06.2017.
 */
public interface UserService extends JpaRepository <User, Long>{

    List<User> findAll ();
    User findByUserId (long id);
    User findByUsername (String username);
    User findByEmail (String email);
}
