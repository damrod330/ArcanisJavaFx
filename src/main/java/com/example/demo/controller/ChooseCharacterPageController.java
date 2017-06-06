package com.example.demo.controller;

import com.example.demo.controller.utility.PageController;
import com.example.demo.controller.utility.Session;
import com.example.demo.interfaces.BootInitializable;
import com.example.demo.model.PlayableCharacter;
import com.example.demo.model.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Damrod on 06.06.2017.
 */
@Component
public class ChooseCharacterPageController implements BootInitializable {

    private ApplicationContext springContext;
    private PageController pageController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //ObservableList<PlayableCharacter> list = user.getCharacter();
        //characterListView.setItems(list);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }

    @FXML
    private BorderPane mainContainer;

    @FXML
    private ListView<PlayableCharacter> characterListView;

    @FXML
    private Button createButton;

    @FXML
    private Button selectButton;

    @FXML
    void buttonLoginClicked(MouseEvent event) {

    }

    @FXML
    void buttonSelectClicked(MouseEvent event) {

    }
}
