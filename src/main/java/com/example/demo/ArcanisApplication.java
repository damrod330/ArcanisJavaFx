package com.example.demo;

import com.example.demo.controller.LoginPageController;
import com.example.demo.controller.MainPageController;
import com.example.demo.controller.PageController;
import com.example.demo.controller.TestController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ArcanisApplication extends Application{

	private static String[] argument;
	private ApplicationContext springContext = null;

	public static void main(String[] args) throws Exception {
		ArcanisApplication.argument = args;
		launch(args);
	}

	public static final String pageLogin = "pageLogin";
	public static final String pageLOginFile = "scene/pages/LoginPage.fxml";

    public static final String pageMain = "pageMain";
    public static final String pageMainFile = "scene/pages/MainPage.fxml";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Task<Object> task = new Task<Object>() {
			@Override
			protected Object call() throws Exception {
				springContext = SpringApplication.run(ArcanisApplication.class, argument);
				return null;
			}
		};

		task.setOnSucceeded((WorkerStateEvent e) -> {
			PageController pageContainer = new PageController();

			LoginPageController loginPageController = springContext.getBean(LoginPageController.class);
            MainPageController mainPageController = springContext.getBean(MainPageController.class);

			pageContainer.loadPageWithContorller(ArcanisApplication.pageLogin, ArcanisApplication.pageLOginFile, loginPageController);
            pageContainer.loadPageWithContorller(pageMain, pageLogin, mainPageController);

			pageContainer.setPage(ArcanisApplication.pageLogin);

			BorderPane root = new BorderPane();
			root.setCenter(pageContainer);

			Scene scene = new Scene(root);
			scene.setOnKeyPressed(event -> {
				if (event.getCode() == KeyCode.F12 && !primaryStage.isFullScreen()) {
					primaryStage.setFullScreen(true);
				}
				else if(event.getCode() == KeyCode.F12 && primaryStage.isFullScreen()){
					primaryStage.setFullScreen(false);
				}
			});
            primaryStage.setMinWidth(800);
			primaryStage.setMinHeight(600);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Arcanis");
			primaryStage.show();

		});
		task.setOnFailed(e -> {
			System.exit(0);
			Platform.exit();
		});
		task.run();
	}
}
