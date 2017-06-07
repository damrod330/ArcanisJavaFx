package com.example.demo.controller.utility;

import com.example.demo.ArcanisApplication;
import com.example.demo.controller.ChooseCharacterPageController;
import com.example.demo.controller.MainMenuController;
import com.example.demo.controller.MainPageController;
import com.example.demo.controller.QuestPageController;
import com.example.demo.model.PlayableCharacter;
import com.example.demo.model.User;
import com.example.demo.services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


/**
 * Created by Damrod on 04.06.2017.
 */
@Component
public class Session implements ApplicationContextAware {

    @Autowired
    UserService userService;

    private ApplicationContext springContext;
    private PageController pageController;

    private BorderPane mainContainer;

    public User getCurrentUser() {
        return currentUser;
    }

    private User currentUser;

    public PlayableCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    private PlayableCharacter currentCharacter;

    public void login(User user) {
        currentUser = user;
        currentUser.setLoginedIn(true);
        userService.save(user);

        List<PlayableCharacter> list =  user.getCharacter();
        currentCharacter = list.get(0);

        //load pages
        MainPageController mainPageController = springContext.getBean(MainPageController.class);
        pageController.loadPageWithContorller(ArcanisApplication.pageMain, ArcanisApplication.pageMainFile, mainPageController);

        pageController.setPage(ArcanisApplication.pageMain);
        //load menu
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/MainMenu.fxml"));
        MainMenuController mainMenuController = springContext.getBean(MainMenuController.class);
        fxmlLoader.setController(mainMenuController);
        try {
            Node mainMenu = fxmlLoader.load();
            mainContainer = (BorderPane)pageController.getParent();
            mainContainer.setTop(mainMenu);
        } catch (IOException e1) {
            e1.printStackTrace();
        }



    }

    public void logout(){
        BorderPane mainContainer = (BorderPane)pageController.getParent();
        mainContainer.setTop(null);

        //load logo
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/scene/MainLogo.fxml"));
        try {
            Node mainMenu = fxmlLoader.load();
            mainContainer = (BorderPane)pageController.getParent();
            mainContainer.setTop(mainMenu);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        springContext = applicationContext;
        pageController = springContext.getBean(PageController.class);
    }
}
