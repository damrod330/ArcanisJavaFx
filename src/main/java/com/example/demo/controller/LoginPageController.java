package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.controller.utility.Session;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private CheckBox rememberMeCheckBox;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Text errorText;

    @FXML
    void checkboxRememberMeClicked(MouseEvent event) {

    }

    @FXML
    void buttonLoginClicked(MouseEvent event) {
        User user = userService.findByUsername(usernameTextField.getText());

        if (user != null) {
            if (user.getPassword().equals(passwordTextField.getText())) {
                errorText.setText("");
                Session session = springContext.getBean(Session.class);
                session.login(user);
            } else {
                errorText.setText("Password is wrong");
            }
        } else {
            errorText.setText("User dosn't exist");
        }
    }

    @FXML
    void buttonRegisterClicked(MouseEvent event) {

    }
}
