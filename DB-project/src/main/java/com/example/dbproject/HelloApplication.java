package com.example.dbproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.geometry.Pos;
import javafx.stage.Modality;


import java.io.IOException;

import java.sql.*;

public class HelloApplication extends Application {

    static final String DB_URL = "jdbc:mysql://localhost:3306/shop";
    static final String USER = "root";
    static final String PASS = "1457914Neg!";

    private Parent createContent() {

        BorderPane root = new BorderPane();


        Label login_label = new Label();
        login_label.setText("LOGIN\n\n");

        TextField username_textField = new TextField();
        username_textField.setText("username");
        username_textField.setMaxSize(100,100);

        TextField password_textField = new TextField();
        password_textField.setText("password(only for customers)");
        password_textField.setMaxSize(200,100);

        Button admin_login_btn = new Button("login as admin");
        Button user_login_btn = new Button("login as user");
        HBox hBox1 = new HBox(admin_login_btn, user_login_btn);
        hBox1.setAlignment(Pos.TOP_CENTER);
        hBox1.setSpacing(10);

        Label space1 = new Label();
        space1.setText("\n\n\n\n\n");



        VBox vBox1 = new VBox(login_label, username_textField,password_textField,space1, hBox1);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setSpacing(10);

        admin_login_btn.setOnAction(e->{
            String username = username_textField.getText();

            try{
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT IsAdmin FROM staff WHERE SName = " + "'" + username + "'");
                int c = 0;
                while (rs.next()) {
                    c++;
                    if (rs.getInt("IsAdmin") == 1){
                        System.out.println("admin logged in successfully");
                        //show admin access list
                    }
                    else {
                        System.out.println("Incorrect username or access");
                    }
                }
                if (c==0){
                    System.out.println("Incorrect username or access");
                }
                con.close();
            }catch(Exception ex){
                System.out.println("An Unknown error happened -_-\n Try again ...");
            }

        });

        user_login_btn.setOnAction(e->{
            String username = username_textField.getText();
            String password = password_textField.getText();

            try{
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
                Statement stmt=con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT UserPassword FROM customerprofile WHERE Username = " + "'" + username + "'");
                int c = 0;
                while (rs.next()) {
                    c++;
                    if (rs.getString("UserPassword").equals(password)){
                        System.out.println("user logged in successfully");
                        StartPage();
                    }
                    else {
                        System.out.println("Incorrect username/password or access");
                    }
                }
                if (c==0){
                    System.out.println("Incorrect username/password or access");
                }
                con.close();
            }catch(Exception ex){
                System.out.println("An Unknown error happened -_-\n Try again ...");
            }

        });

        root.setCenter(vBox1);

        return root;

    }


    public ResultSet executeSqlQuery(String query){
        try{
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM staff");
            con.close();
            return rs;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


    public void StartPage(){


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("User access list");

        BorderPane borderPane = new BorderPane();




        Label title = new Label();
        title.setText("لیست دسترسی های مشتری");
        Label space1 = new Label();
        space1.setText("\n\n\n\n\n");
        Button productsList_btn = new Button("نمایش لیست محصولات");
        Button reviewsList_btn = new Button("نمایش سه نظر برتر محصول انتخابی");
        TextField product_textfield = new TextField("نام محصول");
        product_textfield.setMaxSize(200,100);
        Button usersList_btn = new Button("نمایش کاربران مربوط به یک شهر");
        TextField providers_textfield = new TextField("نام شهر");
        product_textfield.setAlignment(Pos.CENTER);
        providers_textfield.setMaxSize(200,100);
        providers_textfield.setAlignment(Pos.CENTER);
        Button editProfile = new Button("ویرایش اطلاعات");




        VBox vBox = new VBox(title,space1,productsList_btn,reviewsList_btn,product_textfield,usersList_btn,providers_textfield,editProfile);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane,350,500);

        window.setScene(scene);
        window.showAndWait();

    }



    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent(), 300,300);
        stage.setTitle("Shop");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
//
//        try{
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
//            Statement stmt=con.createStatement();
//            ResultSet rs = stmt.executeQuery("select * from staff");
//            while (rs.next()) {
//                System.out.println("ID: " + rs.getInt("ID"));
//            }
//            con.close();
//        }catch(Exception e){ System.out.println(e);}
    }

    public static void main(String[] args) {
        launch();
    }
}

