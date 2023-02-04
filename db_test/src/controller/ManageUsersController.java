package controller;

import db.DBQuery;
import db.DatabaseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.SceneController.*;
import static other.Values.NO_RECORD;

public class ManageUsersController implements Initializable {
    @FXML VBox users_list;

    ArrayList<String[]> result;
    String error;
    String title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUsersPage();
    }

    public void removeUser() {

    }

    public void getUsersQuery() {
        DBQuery query = new DBQuery();
        try {
            result = query.getUsers();
        } catch (DatabaseException e) {
            error = e.getMessage();
        }
        showUsersPage();

    }
    public void showUsersPage() {
        VBox vBox = new VBox();
        VBox container = new VBox();

        HBox labelBox = new HBox();
        Label idLabel = new Label("please enter the ID of user");
        labelBox.getChildren().add(idLabel);

        HBox editBox = new HBox();
        TextField idField = new TextField("id");
        Button remove = new Button("remove");
        Button edit = new Button("edit");
        editBox.setAlignment(Pos.CENTER_LEFT);
        editBox.setPadding(new Insets(0, 0, 30, 0));
        editBox.getChildren().addAll(idField, remove, edit);

        container.getChildren().addAll(labelBox, editBox);

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(container);
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


    public void goToMenu(){
        switchToMenu();
    }



}
