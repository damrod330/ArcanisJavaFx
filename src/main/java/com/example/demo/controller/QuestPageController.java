package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.controller.utility.Session;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.PlayableCharacter;
import com.example.demo.model.Quest;
import com.example.demo.services.PlayableCharactrService;
import com.example.demo.services.QuestService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;


/**
 * Created by Damrod on 06.06.2017.
 */
@Component
public class QuestPageController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;
    private PlayableCharacter character;
    private int timeLeft;
    private boolean started = false;

    @Autowired
    PlayableCharactrService playableCharactrService;

    @Autowired
    QuestService questService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = springContext.getBean(Session.class);
        character = session.getCurrentCharacter();


        questTableView.getItems().clear();

        questTableView.getItems().addAll(questService.findAll());
        title.setCellValueFactory(new PropertyValueFactory<Quest, String>("title"));
        duration.setCellValueFactory(new PropertyValueFactory<Quest, Integer>("duration"));
        hpLoss.setCellValueFactory(new PropertyValueFactory<Quest, String>("hpLoss"));
        reward.setCellValueFactory(new PropertyValueFactory<Quest, Integer>("reward"));

        questTableView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<?extends Quest> values, Quest oldValue, Quest newValue)->
        {
            if(newValue != null){
                setFields(newValue);
            }else {
                clearFields();
            }
        });

        buttonEndQuest.setDisable(true);
        if(character.getQuestinProgress() != null)
        {
            currentQuestTitleText.setText(character.getQuestinProgress().getTitle());
            buttonBeginQuest.setDisable(true);
            Date currentTime = new Date();
            startTimer((int) (character.getQuestionInPregressEndingDate().getTime() - (currentTime.getTime())));
        }
    }

    private Quest selectedQuest;

    private void clearFields() {
    }

    private void setFields(Quest newValue) {
        selectedQuest = newValue;
        titleText.setText(newValue.getTitle());
        descriptionText.setText(newValue.getDescription());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }

    @FXML
    private BorderPane mainContainer;

    @FXML
    private VBox questListContainer;

    @FXML
    private TableView<Quest> questTableView;

    @FXML
    private TableColumn<Quest, String> title;

    @FXML
    private TableColumn<Quest, Integer> duration;

    @FXML
    private TableColumn<Quest, String> hpLoss;

    @FXML
    private TableColumn<Quest, Integer> reward;

    @FXML
    private Text infoText;

    @FXML
    private Button buttonBeginQuest;

    @FXML
    private VBox questListContainer1;

    @FXML
    private Text titleText;

    @FXML
    private Text descriptionText;

    @FXML
    private VBox questListContainer11;

    @FXML
    private Button buttonEndQuest;

    @FXML
    private Text currentQuestTitleText;

    @FXML
    void buttonEndQuestClicked(MouseEvent event) {
        buttonBeginQuest.setDisable(false);
        character.setExperience(character.getExperience()+ character.getQuestinProgress().getExpReward());
        character.setQuestionInPregressEndingDate(null);
        character.setQuestinProgress(null);
        character.setCurrentHp(character.getCurrentHp() - new Random().nextInt(10));
        buttonEndQuest.setText("Select Quest");
        buttonEndQuest.setDisable(true);
        playableCharactrService.save(character);
    }

    @FXML
    void buttonBeginQuestClicked(MouseEvent event) {
        if(selectedQuest !=null){
            character.setQuestinProgress(selectedQuest);

            Timestamp timestamp = new Timestamp(0);
            Date currentTime = new Date();
            timestamp.setTime(currentTime.getTime()+ (selectedQuest.getDuration()*1000));

            character.setQuestionInPregressEndingDate(timestamp);
            playableCharactrService.save(character);
            System.out.println("date set");

            startTimer((int) (character.getQuestionInPregressEndingDate().getTime() - (currentTime.getTime())));
            buttonBeginQuest.setDisable(true);

            currentQuestTitleText.setText(character.getQuestinProgress().getTitle());
        }
        else{
            infoText.setText("Select a quest to Start it");
        }
    }

    private void startTimer( int period){
        if(!started && character.getQuestionInPregressEndingDate() != null) {
            started = true;
            int seconds = period / 1000;
            timeLeft = seconds;
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.millis(1000),
                    ae -> {
                        timeLeft--;
                        System.out.print(timeLeft);
                        buttonEndQuest.setText("Sec left: " + timeLeft);
                    }));
            timeline.setCycleCount(seconds);
            timeline.setOnFinished(event -> {
                started = false;
                System.out.print("done");
                buttonEndQuest.setText("Collect reward");
                buttonEndQuest.setDisable(false);
            });
            timeline.play();
        }
    }
}
