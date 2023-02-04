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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;






import java.io.IOException;

import java.sql.*;


public class HelloApplication extends Application {

    static final String DB_URL = "jdbc:mysql://localhost:3306/shop";
    static final String USER = "root";
    static final String PASS = "1457914Neg!";
    public TextField product_textfield;
    public TextField providers_textfield;
    public TextField users_textfield;

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
                        UserAccessList();
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

    public void usersInCity(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("users");

        BorderPane borderPane = new BorderPane();

        String selected_city = users_textfield.getText();

        ObservableList<User> data = FXCollections.observableArrayList();

        try{
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.ID, c.FullName, c.BirthDate, a.City FROM customer c INNER JOIN address as a ON a.ID = c.AddressID WHERE a.City = '"+ selected_city +"'");

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String fullName = rs.getString("FullName");
                String birthdate = rs.getString("BirthDate");
                String city = rs.getString("City");

                data.add(
                        new User(ID+"", fullName, birthdate, city)
                );
            }
            con.close();
        }catch(Exception ex){
            System.out.println("An Unknown error happened -_-\n Try again ...");
        }


        TableView<User> table = new TableView<User>();


        Label title = new Label();
        title.setText("\nusers list");
        Label space1 = new Label();
        space1.setText("\n\n\n\n");

        table.setEditable(false);

        TableColumn id_col = new TableColumn("ID");
        id_col.setCellValueFactory(
                new PropertyValueFactory<User, String>("ID"));

        TableColumn name_col = new TableColumn("نام");
        name_col.setCellValueFactory(
                new PropertyValueFactory<User, String>("fullName"));

        TableColumn birth_col = new TableColumn("تاریخ تولد");
        birth_col.setCellValueFactory(
                new PropertyValueFactory<User, String>("birthDate"));

        TableColumn city_col = new TableColumn("شهر");
        city_col.setCellValueFactory(
                new PropertyValueFactory<User, String>("city"));

        table.setItems(data);
        table.getColumns().addAll(id_col, name_col, birth_col,city_col);


        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(title,space1, table);
        vbox.setAlignment(Pos.CENTER);


        borderPane.setCenter(vbox);

        Scene scene = new Scene(borderPane,500,500);

        window.setScene(scene);
        window.showAndWait();



    }

    public void providersInCity(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("providers");

        BorderPane borderPane = new BorderPane();

        String selected_city = providers_textfield.getText();

        ObservableList<Provider> data = FXCollections.observableArrayList();

        try{
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT p.ID, p.ProviderName, p.Email, a.City FROM provider p " +
                            "INNER JOIN address as a ON a.ID = p.AddressID WHERE a.City = '" + selected_city + "'"
            );

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String pName = rs.getString("ProviderName");
                String email = rs.getString("Email");
                String city = rs.getString("City");

                data.add(
                        new Provider(ID+"", pName, email, city)
                );
            }
            con.close();
        }catch(Exception ex){
            System.out.println("An Unknown error happened -_-\n Try again ...");
        }


        TableView<Provider> table = new TableView<Provider>();


        Label title = new Label();
        title.setText("\nusers list");
        Label space1 = new Label();
        space1.setText("\n\n\n\n");

        table.setEditable(false);

        TableColumn id_col = new TableColumn("ID");
        id_col.setCellValueFactory(
                new PropertyValueFactory<Provider, String>("ID"));

        TableColumn providerName_col = new TableColumn("نام");
        providerName_col.setCellValueFactory(
                new PropertyValueFactory<Provider, String>("providerName"));

        TableColumn birth_col = new TableColumn("ایمیل");
        birth_col.setCellValueFactory(
                new PropertyValueFactory<Provider, String>("email"));

        TableColumn city_col = new TableColumn("شهر");
        city_col.setCellValueFactory(
                new PropertyValueFactory<Provider, String>("city"));

        table.setItems(data);
        table.getColumns().addAll(id_col, providerName_col, birth_col,city_col);


        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(title,space1, table);
        vbox.setAlignment(Pos.CENTER);


        borderPane.setCenter(vbox);

        Scene scene = new Scene(borderPane,500,500);

        window.setScene(scene);
        window.showAndWait();

    }

    public void threeBestReviews() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("reviews");

        BorderPane borderPane = new BorderPane();

        String selected_product = product_textfield.getText();

        ObservableList<Review> data = FXCollections.observableArrayList();


        try{
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.ProductName, pr.ID, pr.Title, pr.Rating, pr.CreatedAt, pr.Content FROM product_review pr INNER JOIN product as p ON p.ID = pr.ProductID AND p.ProductName = '" + selected_product + "' ORDER BY pr.Rating DESC LIMIT 3");

            while (rs.next()) {
                String ID = rs.getString("ID");
                String pName = rs.getString("ProductName");
                String title = rs.getString("Title");
                String rating = rs.getString("Rating");

                data.add(
                        new Review(ID, pName, title, rating)
                );
            }
            con.close();
        }catch(Exception ex){
            System.out.println("An Unknown error happened -_-\n Try again ...");
        }


        TableView<Review> table = new TableView<Review>();


        Label title = new Label();
        title.setText("\nproducts list");
        Label space1 = new Label();
        space1.setText("\n\n\n\n");

        table.setEditable(false);

        TableColumn id_col = new TableColumn("ID");
        id_col.setCellValueFactory(
                new PropertyValueFactory<Review, String>("ID"));

        TableColumn productName_col = new TableColumn("نام محصول");
        productName_col.setCellValueFactory(
                new PropertyValueFactory<Review, String>("productName"));

        TableColumn title_col = new TableColumn("موضوع");
        title_col.setCellValueFactory(
                new PropertyValueFactory<Review, String>("title"));

        TableColumn rating_col = new TableColumn("امتیاز");
        rating_col.setCellValueFactory(
                new PropertyValueFactory<Review, String>("rating"));

        table.setItems(data);
        table.getColumns().addAll(id_col, productName_col, title_col,rating_col);


        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(title,space1, table);
        vbox.setAlignment(Pos.CENTER);


        borderPane.setCenter(vbox);

        Scene scene = new Scene(borderPane,500,500);

        window.setScene(scene);
        window.showAndWait();

    }

    public void ShowProducts(){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("products");

        BorderPane borderPane = new BorderPane();

        ObservableList<Product> data1 = FXCollections.observableArrayList();


        try{
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.ID, p.Price, p.Brand, p.ProductName, p.Discount, c.CategoryName, p.ShopID FROM product p" +
                    " INNER JOIN category AS c ON p.CategoryID = c.ID");

            while (rs.next()) {
                int ID = rs.getInt("ID");
                String pName = rs.getString("ProductName");
                String brand = rs.getString("Brand");
                int price = rs.getInt("Price");
                int discount = rs.getInt("Discount");
                String category = rs.getString("CategoryName");

                 data1.add(
                        new Product(ID+"", pName, brand, price+"", discount+"", category)
                );
            }
            con.close();
        }catch(Exception ex){
            System.out.println("An Unknown error happened -_-\n Try again ...");
        }




        TableView<Product> table1 = new TableView<Product>();



        Label title = new Label();
        title.setText("\nproducts list");
        Label space1 = new Label();
        space1.setText("\n\n\n\n");

        table1.setEditable(false);

        TableColumn id_col = new TableColumn("ایدی");
        id_col.setCellValueFactory(
                new PropertyValueFactory<Product, String>("ID"));

        TableColumn productName_col = new TableColumn("نام محصول");
        productName_col.setCellValueFactory(
                new PropertyValueFactory<Product, String>("productName"));

        TableColumn brand_col = new TableColumn("برند");
        brand_col.setCellValueFactory(
                new PropertyValueFactory<Product, String>("brand"));

        TableColumn price_col = new TableColumn("قیمت");
        price_col.setCellValueFactory(
                new PropertyValueFactory<Product, String>("price"));

        TableColumn discount_col = new TableColumn("تخفیف");
        discount_col.setCellValueFactory(
                new PropertyValueFactory<Product, String>("discount"));

        TableColumn category_col = new TableColumn("کتگوری");
        category_col.setCellValueFactory(
                new PropertyValueFactory<Product, String>("category"));

        table1.setItems(data1);
        table1.getColumns().addAll(id_col, productName_col, brand_col,price_col,discount_col, category_col);


        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(title,space1, table1);
        vbox.setAlignment(Pos.CENTER);


        borderPane.setCenter(vbox);

        Scene scene = new Scene(borderPane,500,500);

        window.setScene(scene);
        window.showAndWait();

    }

    public void editProfileUser(){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("edit profile");

        BorderPane borderPane = new BorderPane();

        TextField current_username = new TextField();
        current_username.setText("current username");
        current_username.setMaxSize(200,200);


        TextField username = new TextField();
        username.setText("updated username");
        username.setMaxSize(200,200);
        Button username_save_btn = new Button("save new username");



        TextField password = new TextField();
        password.setText("updated password");
        password.setMaxSize(200,200);
        Button password_save_btn = new Button("save new password");


        TextField email = new TextField();
        email.setText("updated email");
        email.setMaxSize(200,200);
        Button email_save_btn = new Button("save new email");


        TextField fullName = new TextField();
        fullName.setText("updated fullName");
        fullName.setMaxSize(200,200);
        Button name_save_btn = new Button("save new name");


        TextField birthDate = new TextField();
        birthDate.setText("updated birthdate");
        birthDate.setMaxSize(200,200);
        Button birthdate_save_btn = new Button("save new birthdate");




        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.getChildren().addAll(current_username, username, username_save_btn,
                password, password_save_btn, email, email_save_btn, birthDate,
                birthdate_save_btn, fullName, name_save_btn);
        vbox.setAlignment(Pos.CENTER);


        borderPane.setCenter(vbox);

        username_save_btn.setOnAction(e -> {
            String cur_username = current_username.getText();
            String temp_username = username.getText();

            if (!cur_username.equals("current username")){
                if (!temp_username.equals("updated username")){
                    try{
                        Connection con=DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
                        Statement stmt=con.createStatement();
                        String sql = "UPDATE customerprofile SET Username=" + "'" + temp_username + "'" +
                                " WHERE Username=" + "'" + cur_username + "'";
                        int rs = stmt.executeUpdate(sql);

                        con.close();
                    }catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("Incorrect username or An Unknown error happened -_-\n Try again ...");
                    }
                }
            }

        });

        password_save_btn.setOnAction(e -> {
            String cur_username = current_username.getText();
            String temp_password = password.getText();
            if (!cur_username.equals("current username")) {
                if (!temp_password.equals("updated password")){
                    try{
                        Connection con=DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/shop","root","1457914Neg!");
                        Statement stmt=con.createStatement();
                        String sql = "UPDATE customerprofile SET UserPassword=" + "'" + temp_password + "'" +
                                " WHERE Username=" + "'" + cur_username + "'";
                        int rs = stmt.executeUpdate(sql);

                        con.close();
                    }catch(Exception ex){
                        System.out.println(ex);
                        System.out.println("An Unknown error happened -_-\n Try again ...");
                    }
                }
            }
            else {
                System.out.println("Incorrect Username");
            }


        });

        email_save_btn.setOnAction(e -> {
            String cur_username = current_username.getText();
            String temp_email = email.getText();

            if (!cur_username.equals("current username")) {
                if (!temp_email.equals("updated email")) {
                    try {
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/shop", "root", "1457914Neg!");
                        Statement stmt = con.createStatement();
                        String sql = "UPDATE customerprofile SET Email=" + "'" + temp_email + "'" +
                                " WHERE Username=" + "'" + cur_username + "'";
                        int rs = stmt.executeUpdate(sql);

                        con.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("An Unknown error happened -_-\n Try again ...");
                    }
                }
            }
        });

        birthdate_save_btn.setOnAction(e -> {
            String cur_username = current_username.getText();
            String temp_birthdate = birthDate.getText();

            int id = -1;

            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/shop", "root", "1457914Neg!");
                Statement stmt = con.createStatement();
                String sql = "SELECT ID FROM customerprofile" +
                        " WHERE Username=" + "'" + cur_username + "'";
                ResultSet rs = stmt.executeQuery(sql);


                while (rs.next()) {
                    id = rs.getInt("ID");
                }
                con.close();

            } catch (Exception ex) {
                System.out.println("An Unknown error happened -_-\n Try again ...");
            }

            if (id != -1) {
                if (!temp_birthdate.equals("updated birthdate")){
                    try {
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/shop", "root", "1457914Neg!");
                        Statement stmt = con.createStatement();
                        String sql = "UPDATE customer SET BirthDate=" + "'" + temp_birthdate + "'" +
                                " WHERE ProfileID="  + id;
                        int rs = stmt.executeUpdate(sql);

                        con.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                        System.out.println("An Unknown error happened -_-\n Try again ...");
                    }
                }
            }
            else {
                System.out.println("Incorrect username");
            }

        });

        name_save_btn.setOnAction(e -> {
            String cur_username = current_username.getText();
            String temp_fullname = fullName.getText();

            int id = -1;

            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/shop", "root", "1457914Neg!");
                Statement stmt = con.createStatement();
                String sql = "SELECT ID FROM customerprofile" +
                        " WHERE Username=" + "'" + cur_username + "'";
                ResultSet rs = stmt.executeQuery(sql);


                while (rs.next()) {
                    id = rs.getInt("ID");
                }
                con.close();

            } catch (Exception ex) {
                System.out.println("An Unknown error happened -_-\n Try again ...");
            }

            if (id != -1) {
                if (!temp_fullname.equals("updated fullName")){
                    try {
                        Connection con = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/shop", "root", "1457914Neg!");
                        Statement stmt = con.createStatement();
                        String sql = "UPDATE customer SET FullName=" + "'" + temp_fullname + "'" +
                                " WHERE ProfileID=" + "'" + id + "'";
                        int rs = stmt.executeUpdate(sql);

                        con.close();
                    } catch (Exception ex) {
                        System.out.println("An Unknown error happened -_-\n Try again ...");
                    }
                }
            }
            else {
                System.out.println("Incorrect username");
            }


        });

        Scene scene = new Scene(borderPane,500,500);

        window.setScene(scene);
        window.showAndWait();

    }

    public void UserAccessList(){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("User access list");

        BorderPane borderPane = new BorderPane();

        Label splitter1 = new Label();
        splitter1.setText("----------------------------");

        Label splitter2 = new Label();
        splitter1.setText("----------------------------");

        Label splitter3 = new Label();
        splitter1.setText("----------------------------");

        Label splitter4 = new Label();
        splitter1.setText("----------------------------");


        Label title = new Label();
        title.setText("لیست دسترسی های مشتری");
        Label space1 = new Label();
        space1.setText("\n");
        Button productsList_btn = new Button("نمایش لیست محصولات");
        Button reviewsList_btn = new Button("نمایش سه نظر برتر محصول انتخابی");
        product_textfield = new TextField("نام محصول");
        product_textfield.setMaxSize(200,100);
        product_textfield.setAlignment(Pos.CENTER);
        Button usersList_btn = new Button("نمایش کاربران مربوط به یک شهر");
        users_textfield = new TextField("نام شهر");
        users_textfield.setMaxSize(200,100);
        users_textfield.setAlignment(Pos.CENTER);
        Button providersList_btn = new Button("نمایش تامین کنندگان مربوط به یک شهر");
        providers_textfield = new TextField("نام شهر");
        providers_textfield.setMaxSize(200,100);
        providers_textfield.setAlignment(Pos.CENTER);
        Button editProfile_btn = new Button("ویرایش اطلاعات");


        VBox vBox = new VBox(title,space1,productsList_btn,splitter1,reviewsList_btn,product_textfield,splitter2,
                usersList_btn,users_textfield,splitter3,providersList_btn,providers_textfield,splitter4,editProfile_btn);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        borderPane.setCenter(vBox);


        productsList_btn.setOnAction(e->{
           ShowProducts();
        });

        reviewsList_btn.setOnAction(e->{
            threeBestReviews();
        });

        usersList_btn.setOnAction(e->{
            usersInCity();
        });

        providersList_btn.setOnAction(e->{
            providersInCity();
        });

        editProfile_btn.setOnAction(e->{
            editProfileUser();
        });



        Scene scene = new Scene(borderPane,350,500);

        window.setScene(scene);
        window.showAndWait();

    }

    public void AdminAccessList(){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("admin access list");

        BorderPane borderPane = new BorderPane();

        Label splitter1 = new Label();
        splitter1.setText("----------------------------");

        Label splitter2 = new Label();
        splitter1.setText("----------------------------");

        Label splitter3 = new Label();
        splitter1.setText("----------------------------");

        Label splitter4 = new Label();
        splitter1.setText("----------------------------");


        Label title = new Label();
        title.setText("لیست دسترسی های ادمین");
        Label space1 = new Label();
        space1.setText("\n");
        Button productsList_btn = new Button("نمایش لیست محصولات");
        Button reviewsList_btn = new Button("نمایش سه نظر برتر محصول انتخابی");
        product_textfield = new TextField("نام محصول");
        product_textfield.setMaxSize(200,100);
        product_textfield.setAlignment(Pos.CENTER);
        Button usersList_btn = new Button("نمایش کاربران مربوط به یک شهر");
        users_textfield = new TextField("نام شهر");
        users_textfield.setMaxSize(200,100);
        users_textfield.setAlignment(Pos.CENTER);
        Button providersList_btn = new Button("نمایش تامین کنندگان مربوط به یک شهر");
        providers_textfield = new TextField("نام شهر");
        providers_textfield.setMaxSize(200,100);
        providers_textfield.setAlignment(Pos.CENTER);


        VBox vBox = new VBox(title,space1,productsList_btn,splitter1,reviewsList_btn,product_textfield,splitter2,
                usersList_btn,users_textfield,splitter3,providersList_btn,providers_textfield,splitter4);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        borderPane.setCenter(vBox);


        productsList_btn.setOnAction(e->{
            ShowProducts();
        });

        reviewsList_btn.setOnAction(e->{
            threeBestReviews();
        });

        usersList_btn.setOnAction(e->{
            usersInCity();
        });

        providersList_btn.setOnAction(e->{
            providersInCity();
        });

//        editProfile_btn.setOnAction(e->{
//
//        });



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
    }

    public static class Review {

        private final SimpleStringProperty ID;
        private final SimpleStringProperty productName;
        private final SimpleStringProperty title;
        private final SimpleStringProperty rating;

        public Review(String ID, String productName, String title, String rating) {
            this.ID = new SimpleStringProperty(ID);
            this.productName = new SimpleStringProperty(productName);
            this.title = new SimpleStringProperty(title);
            this.rating = new SimpleStringProperty(rating);
        }

        public String getID() {
            return ID.get();
        }

        public SimpleStringProperty IDProperty() {
            return ID;
        }

        public void setID(String ID) {
            this.ID.set(ID);
        }

        public String getProductName() {
            return productName.get();
        }

        public SimpleStringProperty productNameProperty() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName.set(productName);
        }

        public String getTitle() {
            return title.get();
        }

        public SimpleStringProperty titleProperty() {
            return title;
        }

        public void setTitle(String title) {
            this.title.set(title);
        }

        public String getRating() {
            return rating.get();
        }

        public SimpleStringProperty ratingProperty() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating.set(rating);
        }
    }

    public static class Product {

        private final SimpleStringProperty ID;
        private final SimpleStringProperty productName;
        private final SimpleStringProperty brand;
        private final SimpleStringProperty price;
        private final SimpleStringProperty discount;
        private final SimpleStringProperty category;

        private Product(String ID, String productName, String brand, String price, String discount, String category) {
            this.ID = new SimpleStringProperty(ID);
            this.productName = new SimpleStringProperty(productName);
            this.brand = new SimpleStringProperty(brand);
            this.price = new SimpleStringProperty(price);
            this.discount = new SimpleStringProperty(discount);
            this.category = new SimpleStringProperty(category);
        }

        public String getID() {
            return ID.get();
        }

        public SimpleStringProperty IDProperty() {
            return ID;
        }

        public void setID(String ID) {
            this.ID.set(ID);
        }

        public String getProductName() {
            return productName.get();
        }

        public SimpleStringProperty productNameProperty() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName.set(productName);
        }

        public String getBrand() {
            return brand.get();
        }

        public SimpleStringProperty brandProperty() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand.set(brand);
        }

        public String getPrice() {
            return price.get();
        }

        public SimpleStringProperty priceProperty() {
            return price;
        }

        public void setPrice(String price) {
            this.price.set(price);
        }

        public String getDiscount() {
            return discount.get();
        }

        public SimpleStringProperty discountProperty() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount.set(discount);
        }

        public String getCategory() {
            return category.get();
        }

        public SimpleStringProperty categoryProperty() {
            return category;
        }

        public void setCategory(String category) {
            this.category.set(category);
        }
    }

    public static class User {

        private final SimpleStringProperty ID;
        private final SimpleStringProperty fullName;
        private final SimpleStringProperty birthDate;
        private final SimpleStringProperty city;

        private User(String ID, String fullName, String birthDate, String city) {
            this.ID = new SimpleStringProperty(ID);
            this.fullName = new SimpleStringProperty(fullName);
            this.birthDate = new SimpleStringProperty(birthDate);
            this.city = new SimpleStringProperty(city);
        }

        public String getID() {
            return ID.get();
        }

        public SimpleStringProperty IDProperty() {
            return ID;
        }

        public void setID(String ID) {
            this.ID.set(ID);
        }

        public String getFullName() {
            return fullName.get();
        }

        public SimpleStringProperty fullNameProperty() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName.set(fullName);
        }

        public String getBirthDate() {
            return birthDate.get();
        }

        public SimpleStringProperty birthDateProperty() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate.set(birthDate);
        }

        public String getCity() {
            return city.get();
        }

        public SimpleStringProperty cityProperty() {
            return city;
        }

        public void setCity(String city) {
            this.city.set(city);
        }
    }

    public static class Provider {
        private final SimpleStringProperty ID;
        private final SimpleStringProperty providerName;
        private final SimpleStringProperty email;
        private final SimpleStringProperty city;

        private Provider(String ID, String providerName, String email, String city) {
            this.ID = new SimpleStringProperty(ID);
            this.providerName = new SimpleStringProperty(providerName);
            this.email = new SimpleStringProperty(email);
            this.city = new SimpleStringProperty(city);
        }

        public String getID() {
            return ID.get();
        }

        public SimpleStringProperty IDProperty() {
            return ID;
        }

        public void setID(String ID) {
            this.ID.set(ID);
        }

        public String getProviderName() {
            return providerName.get();
        }

        public SimpleStringProperty providerNameProperty() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName.set(providerName);
        }

        public String getEmail() {
            return email.get();
        }

        public SimpleStringProperty emailProperty() {
            return email;
        }

        public void setEmail(String email) {
            this.email.set(email);
        }

        public String getCity() {
            return city.get();
        }

        public SimpleStringProperty cityProperty() {
            return city;
        }

        public void setCity(String city) {
            this.city.set(city);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}

