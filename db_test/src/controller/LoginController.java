package controller;

import main.Application;
import db.DBQuery;
import db.DatabaseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static controller.SceneController.switchToSignUp;
import static other.Values.*;

public class LoginController extends Application {
    @FXML
    private TextField tf_user_field;

    @FXML
    private TextField pf_pass_field;

    @FXML
    private Label lbl_message;

    public String message = "";

    public static String username;
    public static String password;

    public void logIn(ActionEvent event) {

        if (inputsAreValid()) {
            DBQuery queryHandler = new DBQuery();
            try {

                queryHandler.login(username, password);
                message = USER_LOGGED;

                showMessage();
                SceneController.switchToMenu();

            } catch (DatabaseException e) {
                message = e.getMessage();
                showMessage();
            }
        }
    }

    public boolean inputsAreValid() {
        username = tf_user_field.getText();
        password = pf_pass_field.getText();

        if (username.isEmpty() || password.isEmpty()) {
            message = Fill_INPUTS;
            showMessage();
            return false;
        }
        else if (password.length() < 8) {
            message = PASSWORD_8_CHARACTER;
            showMessage();
            return false;
        } else {
            message = WAITING;
            showMessage();
            return true;
        }
    }

    public void showMessage() {
        lbl_message.setText(message);
        System.out.println(message);
    }

    public void goSignUp() {
        switchToSignUp();
    }


}
