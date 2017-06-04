package com.example.demo.controller;

import com.example.demo.ArcanisApplication;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 04.06.2017.
 */
@Component
public class LoginPageController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;

    @Autowired
    UserService userService;

    @Override
    public void initConstruct() {
        System.out.println("Controller initialized");
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

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private CheckBox RememberMeCheckBox;

    @FXML
    private Button LoginButton;

    @FXML
    private Button RegisterButton;

    @FXML
    void CheckboxRememberMeClicked(MouseEvent event) {

    }

    @FXML
    void buttonLoginClicked(MouseEvent event) {

        MainPageController mainPageController = springContext.getBean(MainPageController.class);
        pageController.loadPageWithContorller(ArcanisApplication.pageMain, ArcanisApplication.pageLogin, mainPageController);
        pageController.setPage(ArcanisApplication.pageMain);
    }

    @FXML
    void buttonRegisterClicked(MouseEvent event) {

    }
}
