package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static other.Path.LOGIN_PAGE;

public class Application extends javafx.application.Application {
    public static Stage primaryStage;
    public static Scene scene;

    public static boolean isAdmin;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(LOGIN_PAGE));
        scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setTitle("Shop Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}