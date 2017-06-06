package com.example.demo.controller;

import com.example.demo.ArcanisApplication;
import com.example.demo.controller.utility.PageController;
import com.example.demo.controller.utility.Session;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.PlayableCharacter;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
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

    private Button currentButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        highlightClickedButton(currentButton, btnMainPage);
        currentButton = btnMainPage;

        Session session = springContext.getBean(Session.class);
        PlayableCharacter character = session.getCurrentCharacter();
        nicknameText.setText(character.getName());
        hpText.setText(String.valueOf(character.getCurrentHp())+"%");
        levelText.setText(String.valueOf(character.getLevel()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }

    private void highlightClickedButton(Node currentButton, Node newButton){
        if(currentButton!=null) {
            currentButton.getParent().setStyle("");
            VBox vBox = (VBox) currentButton.getParent();
            Text text = (Text) vBox.getChildren().get(1);
            text.setFill(Color.BLACK);
        }
        newButton.getParent().setStyle("-fx-background-color:  #5d6e7f");
        VBox vBox = (VBox) newButton.getParent();
        Text text = (Text) vBox.getChildren().get(1);
        text.setFill(Color.WHITE);
    }

    @FXML
    private HBox menuContainer;

    @FXML
    private Button btnMainPage;

    @FXML
    private Button btnQuestPage;

    @FXML
    private Button btnLoginPage;

    @FXML
    private Text nicknameText;

    @FXML
    private Text levelText;

    @FXML
    private Text hpText;


    @FXML
    void btnLoginPageClicked(MouseEvent event) {
        pageController.setPage(ArcanisApplication.pageLogin);
        highlightClickedButton(currentButton, btnLoginPage);
        currentButton = btnLoginPage;

        Session session = springContext.getBean(Session.class);
        session.logout();
    }

    @FXML
    void btnMainPageClicked(MouseEvent event) {
        pageController.setPage(ArcanisApplication.pageMain);
        highlightClickedButton(currentButton, btnMainPage);
        currentButton = btnMainPage;
    }

    @FXML
    void btnQuestPageClicked(MouseEvent event) {
        QuestPageController questPageController = springContext.getBean(QuestPageController.class);
        pageController.loadPageWithContorller(ArcanisApplication.pageQuest, ArcanisApplication.pageQuestFile, questPageController);

        pageController.setPage(ArcanisApplication.pageQuest);
        highlightClickedButton(currentButton, btnQuestPage);
        currentButton = btnQuestPage;
    }
}
