package controller;

import db.DBQuery;
import db.DatabaseException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.Application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.SceneController.*;
import static other.Values.*;

public class ManageUsersController implements Initializable {
    @FXML VBox users_list;

    ArrayList<String[]> result;
    String error;
    String title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showUsersPage();
    }
    
    private void showUsersPage() {
        getUsersQuery();

        VBox tableBox = new VBox();
        VBox container = new VBox();

        VBox editBox = new VBox();

        HBox hBox1 = new HBox();
        Label idLabel = new Label("please enter the ID of user: ");
        hBox1.getChildren().add(idLabel);

        HBox hBox2 = new HBox();
        TextField idField = new TextField();
        Button remove = new Button("remove");
        remove.setOnAction(e -> {
            removeButtonHandler(idField.getText());
        });
        Button edit = new Button("edit");
        edit.setOnAction(e -> {
            editButtonHandler(idField.getText());
        });
        hBox2.getChildren().addAll(idField, remove, edit);
        hBox2.setPadding(new Insets(10, 0, 0, 0));
        hBox2.setSpacing(15);

        editBox.getChildren().addAll(hBox1, hBox2);
        editBox.setAlignment(Pos.CENTER_LEFT);
        editBox.setPadding(new Insets(15, 0, 30, 15));

        tableBox.setAlignment(Pos.CENTER);
        if (error != null){
            Label label = new Label(error);
            tableBox.getChildren().add(label);
        }
        else if (result.isEmpty()){
            Label label = new Label(NO_RECORD);
            tableBox.getChildren().add(label);
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
                tableBox.getChildren().add(hBox);
            }
        }

        // Put table to a scroll pane
        ScrollPane scrollPane = new ScrollPane(tableBox);
        scrollPane.setPadding(new Insets(20, 20, 20, 20));

        container.getChildren().addAll(editBox, scrollPane);
        // Open a new stage to have new window for result
        Stage stage = Application.primaryStage;
        Scene scene = new Scene(container, 800, 400);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private void getUsersQuery() {
        DBQuery query = new DBQuery();
        try {
            result = query.getUsers();
        } catch (DatabaseException e) {
            error = e.getMessage();
        }
    }

    public void removeButtonHandler(String id) {
        DBQuery query = new DBQuery();
        query.removeUser(id);
        showUsersPage();
    }

    private void editButtonHandler(String id) {
        Application.primaryStage.close();

        Stage stage = new Stage();
        VBox container = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox3 = new HBox();
        HBox hbox4 = new HBox();

        DBQuery query1 = new DBQuery();
        String[] result = query1.getUserInfoById(id);

        Button button = new Button("confirm");
        Label usernameLabel = new Label("username:");
        Label passwordLabel = new Label("password:");
        Label roleLabel = new Label("role:");

        TextField usernameField = new TextField(result[0]);
        TextField passwordField = new TextField(result[1]);

        String roles[] = {"user", "admin"};
        ComboBox roleBox = new ComboBox(FXCollections.observableArrayList(roles));
        if (result[2].equals("0"))
            roleBox.getSelectionModel().select(0);
        else
            roleBox.getSelectionModel().select(1);

        hbox1.getChildren().addAll(usernameLabel, usernameField);
        hbox2.getChildren().addAll(passwordLabel, passwordField);
        hbox3.getChildren().addAll(roleLabel, roleBox);
        hbox4.getChildren().add(button);

        button.setOnAction(e -> {
            System.out.println(roleBox.getValue());
            stage.close();
            DBQuery query2 = new DBQuery();
            String role = "";
            if (roleBox.getValue().equals("user")) {
                role = "0";
            } else {
                role = "1";
            }
            query2.editUser(id, usernameField.getText(), passwordField.getText(), role);
            showUsersPage();
        });

        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);
        hbox4.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        hbox1.setSpacing(10);
        hbox2.setSpacing(10);
        hbox3.setSpacing(10);
        hbox4.setSpacing(10);
        container.setSpacing(20);
        container.getChildren().addAll(hbox1, hbox2, hbox3, hbox4);

        Scene scene = new Scene(container, 800, 400);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

    }



}
