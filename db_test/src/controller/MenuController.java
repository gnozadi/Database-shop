package controller;

import main.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

import static controller.SceneController.*;

public class MenuController implements Initializable {

    @FXML
    VBox vb_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButton("Show Shop", this::goQueryPage);
        if (Application.isAdmin){
            addButton("User management", this::goUserManagementPage);
            addButton("Product management", this::goProductManagementPage);
        }
        addButton("Edit profile", this::goEditProfilePage);
        addButton("Logout", this::logout);
    }

    public void addButton(String text, Runnable action){
        Button button = new Button(text);
        button.setPrefHeight(45);
        button.minWidth(100);
        button.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 50px;");
        button.setOnMouseClicked(event -> {
            action.run();
        });

        vb_parent.getChildren().add(button);
    }

    public void goUserManagementPage(){
        // TODO: implement add/remove user in a new page(mahya)
    }

    public void goProductManagementPage(){
        // TODO: implement add/remove product in a new page(ali)
    }

    public void goEditProfilePage(){
        // TODO: implement edit name and password? :/ (ghazal)
    }

    public void goQueryPage() {
        switchToQuery();
    }

    public void logout() {
        switchToLogin();
    }

}
