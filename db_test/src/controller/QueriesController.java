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

        // show users - mahya
        title = "Show All users";
        addQuery(title, () -> {
            try {
                result = query.getUsers();
            } catch (DatabaseException e) {
                error = e.getMessage();
            }
            showResult(title, result, error);
        });


        // show product categories - Ghazal
        title = "Show All Categories";
        addQuery(title, () -> {
            try {
                result = query.getCategories();
            } catch (DatabaseException e) {
                error = e.getMessage();
            }
            showResult(title, result, error);
        });

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

        // show 10 best user in week - Ghazal
        title = "Show 10 best user in week";
        addQuery(title, () -> {
            try {
                result = query.getBestUsers("week");
            } catch (DatabaseException e) {
                error = e.getMessage();
            }
            showResult(title, result, error);
        });

        // show 10 best user in month - Ghazal
        title = "Show 10 best user in month";
        addQuery(title, () -> {
            try {
                result = query.getBestUsers("month");
            } catch (DatabaseException e) {
                error = e.getMessage();
            }
            showResult(title, result, error);
        });

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

        // show the list of the cheapest sellers of items for the admin - mahya
        title = "Show the Most Cheap Selling Provider";
        if (main.Application.isAdmin) {
            addQuery(title, () -> {
                try {
                    result = query.getTheMostCheapSellingProviders();
                } catch (DatabaseException e) {
                    error = e.getMessage();
                }
                showResult(title, result, error);
            });
        }


        // TODO:show The user's last 10 orders(ali)

        // TODO:show reviews about a product(ali)

        // show 3 least score review (bita)
        title = "Show 3 Least Score Reviews";
        addQuery(title, () -> getInput("Enter product name", title, query::get3LestReview));

        // show amount of sales of one product per month for the admin - Ghazal
        title = "Show sales of one product per month";
        addQuery(title, () -> {
            try {
                result = query.salesOfOneProductInMonth();
            } catch (DatabaseException e) {
                error = e.getMessage();
            }
            showResult(title, result, error);
        });


        // show Average store sales per month for admin - mahya
        title = "show Average store sales per month for admin";
        if (main.Application.isAdmin) {
            addQuery(title, () -> {
                try {
                    result = query.getAverageSalesPerMonth();
                } catch (DatabaseException e) {
                    error = e.getMessage();
                }
                showResult(title, result, error);
            });
        }


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
