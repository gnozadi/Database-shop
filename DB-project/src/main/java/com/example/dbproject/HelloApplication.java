package com.example.dbproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import java.sql.*;

public class HelloApplication extends Application {

    static final String DB_URL = "jdbc:mysql://localhost/shop";
    static final String USER = "root";
    static final String PASS = "1457914Neg!";
    static final String QUERY = "SELECT * FROM staff";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        try{
//            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from staff");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("ID"));

            }
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void main(String[] args) {
        launch();
    }
}

