package com.example.demo.services;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Damrod on 02.06.2017.
 */
@Component
public interface UserService extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
}
