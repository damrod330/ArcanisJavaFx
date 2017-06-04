package com.example.demo.controller;

import com.example.demo.BootInitializable;
import com.example.demo.services.UserService;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 24.05.2017.
 */

@Component
public class TestController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;

    @Autowired
    UserService userService;

    @Override
    public void initConstruct() {
        System.out.println(userService.findByUserId((1)).getUsername());
    }

    @Override
    public void setPageParrent(PageController parentPage) {
        pageController = parentPage;
    }


    @Override
    public void stage(Stage primaryStage) {
    }

    @Override
    public Node initView() {
        return null;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initConstruct();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }
}
