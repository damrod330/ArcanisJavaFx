package com.example.demo;

import com.example.demo.controller.LoginPageController;
import com.example.demo.controller.utility.PageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ArcanisApplication extends Application{

	private static String[] argument;
	private ApplicationContext springContext = null;

	public static void main(String[] args) throws Exception {
		ArcanisApplication.argument = args;
		launch(args);
	}

	public static final String pageLogin = "pageLogin";
	public static final String pageLOginFile = "scene/Pages/LoginPage.fxml";

    public static final String pageRegister = "pageRegister";
    public static final String pageRegisterFile = "scene/Pages/RegisterPage.fxml";

	public static final String pageMain = "pageMain";
	public static final String pageMainFile = "scene/Pages/MainPage.fxml";

	public static final String pageQuest = "pageQuest";
	public static final String pageQuestFile = "scene/Pages/QuestPage.fxml";


	public static final String mainMenu = "MainMenu";
	public static final String mainMenuFile = "scene/MainMenu.fxml";

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
			PageController pageContainer = springContext.getBean(PageController.class);

			LoginPageController loginPageController = springContext.getBean(LoginPageController.class);

			pageContainer.loadPageWithContorller(ArcanisApplication.pageLogin, ArcanisApplication.pageLOginFile, loginPageController);

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
