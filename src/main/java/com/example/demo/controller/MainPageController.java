package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.interfaces.BootInitializable;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 04.06.2017.
 */
@Component
public class MainPageController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;

    @Override
    public void setPageParrent(PageController parentPage) {
        pageController = parentPage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }
}
