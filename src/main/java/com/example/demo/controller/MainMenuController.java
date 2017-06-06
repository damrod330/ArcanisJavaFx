package com.example.demo.controller.utility;

import com.example.demo.ArcanisApplication;
import com.example.demo.controller.utility.PageController;
import com.example.demo.interfaces.BootInitializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 06.06.2017.
 */
@Component
public class MainMenuController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }


    @FXML
    private Button btnMainPage;

    @FXML
    private Button btnQuestPage;

    @FXML
    private Button btnLoginPage;

    @FXML
    void btnLoginPageClicked(MouseEvent event) {
        pageController.setPage(ArcanisApplication.pageLogin);
    }

    @FXML
    void btnMainPageClicked(MouseEvent event) {
        pageController.setPage(ArcanisApplication.pageMain);
    }

    @FXML
    void btnQuestPageClicked(MouseEvent event) {

    }
}
