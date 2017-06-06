package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.controller.utility.Session;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.PlayableCharacter;
import com.example.demo.model.Quest;
import com.example.demo.services.PlayableCharactrService;
import com.example.demo.services.QuestService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

    @Autowired
    QuestService questService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Session session = springContext.getBean(Session.class);
        character = session.getCurrentCharacter();
        updateAllViews();

        questTableView.getItems().clear();

        questTableView.getItems().addAll(questService.findAll());
        title.setCellValueFactory(new PropertyValueFactory<Quest, String>("title"));
        description.setCellValueFactory(new PropertyValueFactory<Quest, String>("description"));
    }

    private void updateAllViews() {
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
    private TableColumn<Quest, String> description;

    @FXML
    private Button buttonBeginQuest;

    @FXML
    private VBox questListContainer1;

    @FXML
    void buttonBeginQuestClicked(MouseEvent event) {

    }
}
