package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.controller.utility.Session;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.PlayableCharacter;
import com.example.demo.services.PlayableCharactrService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 06.06.2017.
 */
@Component
public class QuestPageController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;
    private PlayableCharacter character;

    @Autowired
    PlayableCharactrService playableCharactrService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = springContext.getBean(Session.class);
        character = session.getCurrentCharacter();
        updateAllViews();
    }

    private void updateAllViews() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }
}
