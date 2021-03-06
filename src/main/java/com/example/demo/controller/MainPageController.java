package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.controller.utility.Session;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.PlayableCharacter;
import com.example.demo.model.User;
import com.example.demo.services.PlayableCharactrService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 04.06.2017.
 */
@Component
public class MainPageController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;
    private PlayableCharacter character;

    @Autowired
    PlayableCharactrService playableCharactrService;

    private void updateAllViews(){
        nicknameText.setText(character.getName());
        levelText.setText(String.valueOf(character.getLevel()));
        expText.setText(String.valueOf(character.getExperience()) + "/"+character.getExperienceRequaiered());
        hpText.setText(String.valueOf(character.getCurrentHp()) + "/"+ String.valueOf(character.getMaxHp()));
        strText.setText(String.valueOf(character.getStrenght()));
        dexText.setText(String.valueOf(character.getDexterity()));
        intText.setText(String.valueOf(character.getInteligence()));
        powerText.setText(String.valueOf(character.getStrenght() + character.getDexterity() + character.getInteligence()));
        if(character.getUnusedPoints() == 0) {
            pointsAvalibleText.setText("");
            buttonAddStr.setVisible(false);
            buttonAddDex.setVisible(false);
            buttonAddInt.setVisible(false);
        }
        else {
            pointsAvalibleText.setText(String.valueOf(character.getUnusedPoints())+ " points avalible");
            buttonAddStr.setVisible(true);
            buttonAddDex.setVisible(true);
            buttonAddInt.setVisible(true);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = springContext.getBean(Session.class);
        character = session.getCurrentCharacter();
        updateAllViews();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }

    @FXML
    private BorderPane mainContainer;

    @FXML
    private Text nicknameText;

    @FXML
    private Text guildText;

    @FXML
    private Text levelText;

    @FXML
    private Text hpText;

    @FXML
    private Text strText;

    @FXML
    private Text dexText;

    @FXML
    private Text intText;

    @FXML
    private Text expText;

    @FXML
    private Text powerText;

    @FXML
    private Button buttonAddStr;

    @FXML
    private Button buttonAddDex;

    @FXML
    private Button buttonAddInt;

    @FXML
    private Text pointsAvalibleText;

    @FXML
    private Button buttonLevelUp;

    @FXML
    void buttonAddDexClicked(MouseEvent event) {
        character.setDexterity(character.getDexterity()+1);
        character.setUnusedPoints(character.getUnusedPoints()-1);
        updateAllViews();
        playableCharactrService.save(character);
    }

    @FXML
    void buttonAddIntClicked(MouseEvent event) {
        character.setInteligence(character.getInteligence()+1);
        character.setUnusedPoints(character.getUnusedPoints()-1);
        updateAllViews();
        playableCharactrService.save(character);
    }

    @FXML
    void buttonAddStrClicked(MouseEvent event) {
        character.setStrenght(character.getStrenght()+1);
        character.setUnusedPoints(character.getUnusedPoints()-1);
        updateAllViews();
        playableCharactrService.save(character);
    }


    @FXML
    void buttonLevelUpClicked(MouseEvent event) {
        if(character.getExperience() >= character.getExperienceRequaiered()) {
            character.setExperience(character.getExperience() -character.getExperienceRequaiered());
            character.setUnusedPoints(character.getUnusedPoints()+10);
            character.setLevel(character.getLevel()+1);
            character.setExperienceRequaiered(character.getExperienceRequaiered()+ 20*character.getExperienceRequaiered()/100);
            character.setCurrentHp(character.getMaxHp());
            playableCharactrService.save(character);
            updateAllViews();
        }
        else {
            pointsAvalibleText.setText("not enough exp");
        }
    }

}
