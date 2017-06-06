package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.interfaces.BootInitializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    public void initialize(URL location, ResourceBundle resources) {

        //load menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/MainMenu.fxml"));
        MainMenuController mainMenuController = springContext.getBean(MainMenuController.class);
        fxmlLoader.setController(mainMenuController);
        try {
            Node mainMenu = fxmlLoader.load();
            mainContainer.setTop(mainMenu);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }

    @FXML
    private BorderPane mainContainer;
}
