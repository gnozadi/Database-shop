package controller;

import db.DBQuery;
import db.DatabaseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static controller.SceneController.switchToLogin;
import static other.Values.*;

public class SignUpController {

    @FXML
    private TextField tf_user_field;

    @FXML
    private TextField tf_email_field;

    @FXML
    private TextField pf_pass_field;

    @FXML
    private TextField pf_repeat_pass_field;

    @FXML
    private Label lbl_message;

    public String message = "";

    String username;
    String password;

    public void signUp(ActionEvent event) {
        if (inputsAreValid()) {
            DBQuery queryHandler = new DBQuery();
            try {
                queryHandler.signUp(username, password);

                message = USER_SIGNED_UP;
                showMessage();
                SceneController.switchToLogin();

            } catch (DatabaseException e) {
                message = e.getMessage();
                showMessage();
            }
        }
    }

    public boolean inputsAreValid() {
        username = tf_user_field.getText();
        String email = tf_email_field.getText();
        password = pf_pass_field.getText();
        String repeatPass = pf_repeat_pass_field.getText();

        boolean isValid = false;
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPass.isEmpty()) {
            message = Fill_INPUTS;
        } else if (password.length() < 8) {
            message = PASSWORD_8_CHARACTER;
        } else if (!password.equals(repeatPass)) {
            message = PASS_WITH_REPEAT_NOT_MATCH;
        } else if (!email.contains(".com") || !email.contains("@")) {
            message = INVALID_EMAIL;
        } else {
            message = WAITING;
            isValid = true;
        }

        showMessage();
        return isValid;
    }

    public void showMessage() {
        lbl_message.setText(message);
        System.out.println(message);
    }

    public void goLogin() {
        switchToLogin();
    }

}

