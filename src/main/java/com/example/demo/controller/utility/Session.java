package com.example.demo.controller.utility;

import com.example.demo.ArcanisApplication;
import com.example.demo.controller.MainPageController;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * Created by Damrod on 04.06.2017.
 */
@Component
public class Session implements ApplicationContextAware {

    @Autowired
    UserService userService;

    private ApplicationContext springContext;
    private PageController pageController;

    public User getCurrentUser() {
        return currentUser;
    }

    private User currentUser;

    public void login(User user) {
        MainPageController mainPageController = springContext.getBean(MainPageController.class);
        pageController.loadPageWithContorller(ArcanisApplication.pageMain, ArcanisApplication.pageMainFile, mainPageController);
        pageController.setPage(ArcanisApplication.pageMain);
        currentUser = user;
        currentUser.setLoginedIn(true);
        userService.save(user);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }
}
