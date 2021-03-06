package com.example.demo.controller;

import com.example.demo.ArcanisApplication;
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
        colorizePage();
    }

    private void colorizePage(){
//        ImageAnalizer imageAnalizer = springContext.getBean(ImageAnalizer.class);
//        try {
//            BufferedImage img = ImageIO.read(new File("/images/icons/icon01.jpg"));
//
//            final float FACTOR  = 10f;
//            int scaleX = (int) (img.getWidth() * FACTOR);
//            int scaleY = (int) (img.getHeight() * FACTOR);
//            Image image = img.getScaledInstance(scaleX, scaleY, Image.SCALE_SMOOTH);
//            BufferedImage buffered = new BufferedImage(scaleX, scaleY, RGBA);
//            buffered.getGraphics().drawImage(image, 0, 0 , null);
//
//
//            List<String> colors = imageAnalizer.getColors(img);
//
//            loginButton.setStyle("-fx-background-color: "+colors.get(0)+";");
//            registerButton.setStyle("-fx-background-color: "+colors.get(1)+";");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        RegisterPageController registerPageController = springContext.getBean(RegisterPageController.class);
        pageController.loadPageWithContorller(ArcanisApplication.pageRegister, ArcanisApplication.pageRegisterFile, registerPageController);
        pageController.setPage(ArcanisApplication.pageRegister);
    }
}
