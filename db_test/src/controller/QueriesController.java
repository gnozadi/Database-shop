package controller;

import other.ParamHandler;
import db.DBQuery;
import db.DatabaseException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controller.SceneController.showResult;
import static other.Values.Fill_INPUT;

public class QueriesController implements Initializable {

    @FXML
    VBox query_list;

    ArrayList<String[]> result;
    String error;
    String title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addSelectQueries();
    }

    // Construct suggested query list
    //  Open a new window to show query result
    public void addSelectQueries() {
        DBQuery query = new DBQuery();

        // TODO:show users(mahya)

        // TODO:show product categories(ghazal)

        // show orders - Bita
        title = "Show All Orders";
        addQuery(title, () -> {
            try {
                result = query.getOrders();
            } catch (DatabaseException e) {
                error = e.getMessage();
            }
            showResult(title, result, error);
        });

        // TODO:show 10 best user in week(ghazal)
        // TODO:show 10 best user in month(ghazal)

        // TODO:show best selling product in week(ali)
        // TODO:show best selling product in month(ali)

        // Show suggested products - more than 15% discount(bita)
        title = "Show Suggested Products";
        addQuery(title, () -> {
            try {
                result = query.getSuggestedProducts();
            } catch (DatabaseException e) {
                error = e.getMessage();
            }
            showResult(title, result, error);
        });

        // TODO:show the list of the cheapest sellers of items for the admin(mahya)
        // check isAdmin in Application

        // TODO:show The user's last 10 orders(ali)

        // TODO:show reviews about a product(ali)

        // show 3 least score review (bita)
        title = "Show 3 Least Score Reviews";
        addQuery(title, () -> getInput("Enter product name", title, query::get3LestReview));

        // TODO:show amount of sales of one product per month for the admin (ghazal)
        // check isAdmin in Application

        // TODO:show Average store sales per month for admin (mahya)

        // show users from a city
        title = "Show fellow-citizen users";
        addQuery(title, () -> getInput("Enter City name", title, query::getUsersOfACity));

        // show users from a city
        title = "Show fellow-citizen providers";
        addQuery(title, () -> getInput("Enter City name", title, query::getProvidersOfACity));

    }

    // Add new query row to query list
    public void addQuery(String text, Runnable action) {
        Label label = new Label(text);
        label.setPadding(new Insets(20));
        label.setFont(new Font("System", 30));
        label.setAlignment(Pos.CENTER);

        label.setOnMouseClicked(event -> action.run());
        query_list.getChildren().add(label);
    }

    public static void getInput(String hint, String title, ParamHandler action) {
        VBox vBox = new VBox();
        vBox.setSpacing(100);
        vBox.setAlignment(Pos.CENTER);

        // TODO نمیدونم چرا کوچیک نمیشه :/
        TextField textField = new TextField();
        textField.setPromptText(hint);
        vBox.getChildren().add(textField);

        Button button = new Button("Enter");
        button.setPrefHeight(50);
        button.setPrefWidth(100);
        button.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #000000; -fx-border-radius: 50px;");

        button.setOnMouseClicked(event -> {
            String text = textField.getText();
            if (text.isEmpty())
                System.out.println(Fill_INPUT);
            else{
                Stage stage = (Stage) button.getParent().getScene().getWindow();
                stage.close();

                ArrayList<String[]> result = new ArrayList<>();
                try {
                    result = action.doAction(textField.getText());
                    showResult(title, result, null);
                } catch (DatabaseException e) {
                    showResult(title, result, e.getMessage());
                }
            }
        });
        vBox.getChildren().add(button);

        Stage stage = new Stage();
        Scene scene = new Scene(vBox, 600, 400);
        stage.setTitle("Get input");
        stage.setScene(scene);
        stage.show();
    }

}
