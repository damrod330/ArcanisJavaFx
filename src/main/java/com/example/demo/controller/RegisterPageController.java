package com.example.demo.controller;

import com.example.demo.ArcanisApplication;
import com.example.demo.controller.utility.PageController;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.PlayableCharacter;
import com.example.demo.model.User;
import com.example.demo.services.PlayableCharactrService;
import com.example.demo.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 06.06.2017.
 */
@Component
public class RegisterPageController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;

    @Autowired
    UserService userService;

    @Autowired
    PlayableCharactrService playableCharactrService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }

    @FXML
    private BorderPane mainContainer;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;


    @FXML
    private PasswordField passwordRepeatTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Text errorText;

    @FXML
    void buttonLoginClicked(MouseEvent event) {
        pageController.setPage(ArcanisApplication.pageLogin);
    }

    @FXML
    void buttonRegisterClicked(MouseEvent event) {
        if (userService.findByUsername(usernameTextField.getText()) == null) {
            if (validate()) {
                registerAccount();
            }
            else{
                errorText.setText("Incorrect data");
            }
        } else {
            errorText.setText("User alredy exists");
        }

    }

    private boolean validate() {

        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String rePassword = passwordRepeatTextField.getText();
        String email = emailTextField.getText();

        if(username.isEmpty()|| password.isEmpty() || email.isEmpty())
            return  false;
        if (!password.equals(rePassword))
            return  false;
        if (!email.contains("@"))
            return  false;
        return true;
    }

    private void registerAccount() {
        User newUser = new User();
        newUser.setUsername(usernameTextField.getText());
        newUser.setPassword(passwordTextField.getText());
        newUser.setEmail(emailTextField.getText());


        errorText.setText("Account registered");
        newUser.setCharacter(createCharacter(newUser));
        userService.save(newUser);
    }

    private List<PlayableCharacter> createCharacter(User user) {
        List <PlayableCharacter> list = new ArrayList<>();
        PlayableCharacter playableCharacter = new PlayableCharacter();

        playableCharacter.setName(user.getUsername());
        playableCharacter.setExperienceRequaiered(500);
        playableCharacter.setExperience(0);
        playableCharacter.setUnusedPoints(10);
        playableCharacter.setLevel(1);

        playableCharacter.setStrenght(new Random().nextInt(15));
        playableCharacter.setInteligence(new Random().nextInt(15));
        playableCharacter.setDexterity(new Random().nextInt(15));

        playableCharacter.setCurrentHp(100);
        playableCharacter.setMaxHp(100);

        playableCharactrService.save(playableCharacter);
        playableCharacter = playableCharactrService.findByName(user.getUsername());
        list.add(playableCharacter);
        errorText.setText(errorText.getText()+" and created character");
        return list;
    }

}
