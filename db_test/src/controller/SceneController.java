package controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Application;

import java.io.IOException;
import java.util.ArrayList;

import static other.Path.*;
import static other.Values.NO_RECORD;

public class SceneController {

    // Go to Login page
    public static void switchToLogin() {
        navigate(LOGIN_PAGE);
    }

    // Go to Sign up page
    public static void switchToSignUp() {
        navigate(SIGN_UP_PAGE);
    }

    // Go to Menu Page
    public static void switchToMenu() {
        navigate(MENU_PAGE);
    }

    // Go to Query page
    public static void switchToQuery() {
        navigate(QUERY_PAGE);
    }

    public static void switchToEditProfile() {
        navigate(EDIT_PROFILE_PAGE);
    }

    public static void navigate(String path) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Application.scene.setRoot(parent);
    }

    // Open a new window to show Result page
    public static void showResult(String title, ArrayList<String[]> result, String error) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        if (error != null){
            Label label = new Label(error);
            vBox.getChildren().add(label);
        }
        else if (result.isEmpty()){
            Label label = new Label(NO_RECORD);
            vBox.getChildren().add(label);
        }
        else {
            for (String[] row : result) {

                // Define a hBox as a row
                HBox hBox = new HBox();
                for (String s : row) {

                    // Construct a label for each data element
                    //  and Form rows and columns with hBoxes and vBox
                    Label label = new Label(s);
                    label.setMinWidth(150);
                    label.setTextAlignment(TextAlignment.CENTER);
                    hBox.getChildren().add(label);
                }
                vBox.getChildren().add(hBox);
            }
        }

        // Put table to a scroll pane
        ScrollPane scrollPane = new ScrollPane(vBox);
        scrollPane.setPadding(new Insets(20, 20, 20, 20));

        // Open a new stage to have new window for result
        Stage stage = new Stage();
        Scene scene = new Scene(scrollPane, 800, 400);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
