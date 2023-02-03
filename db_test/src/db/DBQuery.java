package db;

import main.Application;
import other.Values;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static other.Values.*;

public class DBQuery {

    private Statement statement;

    public DBQuery() {
        Connection connection = DBConnection.getConnection();
        try {
            this.statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // region authentication
    public void login(String username, String password) throws DatabaseException {
        if (!usernameExists(username)) {
            throw new NotFoundUser();
        } else if (!usernamePasswordMatching(username, password)) {
            throw new MismatchUserPass();
        } else {
            String query = "select * from users where username='" + username + "' and password='" + password + "';";
            try {
                // Execute query
                ResultSet result = statement.executeQuery(query);

                // Go first column
                result.next();

                // Check value of admin column in user table
                if (result.getString(ADMIN_COL).equals(Values.ADMIN_VALUE)) {
                    Application.isAdmin = true;
                } else if (result.getString(ADMIN_COL).equals(Values.NORMAL_USER_VALUE)) {
                    Application.isAdmin = false;
                } else {
                    // Throw an exception if result has not valid value
                    throw new DatabaseException(DEFAULT_ERROR_MESSAGE);
                }
                System.out.println(Application.isAdmin);
            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    private boolean usernamePasswordMatching(String username, String password) throws DatabaseException {
        String query = "select EXISTS(SELECT * FROM USERS WHERE username='" + username + "'and password='" + password + "')";
        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);

            // Go first column
            result.next();

            return checkExistence(result);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public boolean usernameExists(String username) throws DatabaseException {
        String query = "select EXISTS(SELECT * FROM USERS WHERE username='" + username + "')";
        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);

            // Go first column
            result.next();
            return checkExistence(result);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public static boolean checkExistence(ResultSet result) throws DatabaseException {
        // Check value of value of column in 0 or 1
        int resultValue;
        try {
            resultValue = result.getInt(1);
            if (resultValue == EXIST_VALUE) {
                return true;
            } else if (resultValue == NOT_EXIST_VALUE) {
                return false;
            } else {
                throw new DatabaseException(DEFAULT_ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void signUp(String username, String password) throws DatabaseException {
        try {
            if (usernameExists(username)) {
                throw new RepeatedUser();
            }

            String query = "INSERT INTO users (username,password) VALUES ('" + username + "', '" + password + "');";
            // Execute query
            statement.execute(query);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void changeUsername(String newUsername, String oldUsername, String password) throws DatabaseException {
        try {
            if (usernameExists(newUsername)) {
                throw new RepeatedUser();
            }

            String query = "update users set username='" + newUsername + "' where password='" + password
                    +"' and  username='"+oldUsername+"' ;";
            // Execute query
            statement.execute(query);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public void changePassword(String username, String newPassword, String oldPassword) throws SQLException {
        try {
            String query = "update users set password='" + newPassword +"' where password='" + oldPassword
                    +"' and  username='"+username+"' ;";

            // Execute query
            statement.execute(query);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    //endregion

    // region shop
    public ArrayList<String[]> getOrders() throws DatabaseException {
        // Merge table factor and shopping cart to have all order list
        String query = "select factor.ID, TotalPrice, TotalDiscount, 'Date' from factor, shoppingcart where FactorID = factor.ID;";

        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);

            // define an array list to contain result of query
            ArrayList<String[]> tableResult = new ArrayList<>();

            // Add table headers to array list
            tableResult.add(new String[]{ID_COL, TOTAL_PRICE_COL, TOTAL_DISCOUNT_COL, DATE_COL});
            // Add an empty row to have a space between headers and data rows
            tableResult.add(new String[]{"", "", "", ""});

            // Iterate over rows
            while (result.next()) {

                // Get values from columns
                String id = result.getString(ID_COL);
                String price = result.getString(TOTAL_PRICE_COL);
                String discount = result.getString(TOTAL_DISCOUNT_COL);
                String date = result.getString(DATE_COL);

                // Insert values to array list as a new row
                tableResult.add(new String[]{id, price, discount, date});
            }
            return tableResult;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(DEFAULT_ERROR_MESSAGE);
        }
    }

    public ArrayList<String[]> getSuggestedProducts() throws DatabaseException {
        // Merge table factor and shopping cart to have all order list
        String query = "select "
                + ID_COL + ","
                + PRICE_COL + ","
                + BRAND_COL + ","
                + PRODUCT_NAME_COL + ","
                + DISCOUNT_COL + " from product where Discount>=15;";

        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);

            // define an array list to contain result of query
            ArrayList<String[]> tableResult = new ArrayList<>();

            // Add table headers to array list
            tableResult.add(new String[]{ID_COL, PRICE_COL, BRAND_COL, PRODUCT_NAME_COL, DISCOUNT_COL});

            // Add an empty row to have a space between headers and data rows
            tableResult.add(new String[]{"", "", "", ""});

            // Iterate over rows
            while (result.next()) {

                // Get values from columns
                String id = result.getString(ID_COL);
                String price = result.getString(PRICE_COL);
                String brand = result.getString(BRAND_COL);
                String productName = result.getString(PRODUCT_NAME_COL);
                String discount = result.getString(DISCOUNT_COL);

                // Insert values to array list as a new row
                tableResult.add(new String[]{id, price, brand, productName, discount});
            }
            return tableResult;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(DEFAULT_ERROR_MESSAGE);
        }
    }

    public ArrayList<String[]> get3LestReview(String product) throws DatabaseException {
        if (!productExist(product)) {
            throw new NotFoundProduct();
        } else {
            // Merge table factor and shopping cart to have all order list
            String query = "select *" +
                    "from product, product_review where productID = product.ID && ProductName ='" + product + "'order by Rating limit 3";

            try {
                // Execute query
                ResultSet result = statement.executeQuery(query);

                // define an array list to contain result of query
                ArrayList<String[]> tableResult = new ArrayList<>();

                // Add table headers to array list
                tableResult.add(new String[]{PRODUCT_NAME_COL, BRAND_COL, PRICE_COL, TITLE_COL, RATING_COL});

                // Add an empty row to have a space between headers and data rows
                tableResult.add(new String[]{"", "", "", ""});

                // Iterate over rows
                while (result.next()) {

                    // Get values from columns
                    String name = result.getString(PRODUCT_NAME_COL);
                    String brand = result.getString(BRAND_COL);
                    String price = result.getString(PRICE_COL);
                    String title = result.getString(TITLE_COL);
                    String rating = result.getString(RATING_COL);

                    // Insert values to array list as a new row
                    tableResult.add(new String[]{name, price, brand, title, rating});
                }
                return tableResult;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    public boolean productExist(String product) throws DatabaseException {
        String query = "select EXISTS(SELECT * FROM Product WHERE ProductName='" + product + "')";
        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);

            // Go first column
            result.next();
            return checkExistence(result);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ArrayList<String[]> getUsersOfACity(String city) throws DatabaseException {
        if (!cityExist(city)) {
            throw new NotFoundCity();
        } else {
            String query = "select City,Country,FullName,PostalCode, Phone from customer, address where customer.AddressID=address.ID and City='" + city + "'";

            try {
                // Execute query
                ResultSet result = statement.executeQuery(query);

                // define an array list to contain result of query
                ArrayList<String[]> tableResult = new ArrayList<>();

                // Add table headers to array list
                tableResult.add(new String[]{FULL_NAME_COL, COUNTRY_COL,CITY_COL, POSTAL_CODE_COL, PHONE_COL});

                // Add an empty row to have a space between headers and data rows
                tableResult.add(new String[]{"", ""});

                // Iterate over rows
                while (result.next()) {

                    // Get values from columns
                    String name = result.getString(FULL_NAME_COL);
                    String country = result.getString(COUNTRY_COL);
                    String postalCode = result.getString(POSTAL_CODE_COL);
                    String phone = result.getString(PHONE_COL);

                    // Insert values to array list as a new row
                    tableResult.add(new String[]{name, country,city, postalCode, phone});
                }
                return tableResult;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    public ArrayList<String[]> getProvidersOfACity(String city) throws DatabaseException {
        if (!cityExist(city)) {
            throw new NotFoundCity();
        } else {
            String query = "select City,Country,PName,PostalCode, Phone from provider, address where provider.AddressID=address.ID and City='" + city + "'";

            try {
                // Execute query
                ResultSet result = statement.executeQuery(query);

                // define an array list to contain result of query
                ArrayList<String[]> tableResult = new ArrayList<>();

                // Add table headers to array list
                tableResult.add(new String[]{PROVIDER_NAME_COL, COUNTRY_COL,CITY_COL, POSTAL_CODE_COL, PHONE_COL});

                // Add an empty row to have a space between headers and data rows
                tableResult.add(new String[]{"", ""});

                // Iterate over rows
                while (result.next()) {

                    // Get values from columns
                    String name = result.getString(PROVIDER_NAME_COL);
                    String country = result.getString(COUNTRY_COL);
                    String postalCode = result.getString(POSTAL_CODE_COL);
                    String phone = result.getString(PHONE_COL);

                    // Insert values to array list as a new row
                    tableResult.add(new String[]{name, country,city, postalCode, phone});
                }
                return tableResult;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DatabaseException(e.getMessage());
            }
        }
    }

    public boolean cityExist(String city) throws DatabaseException {
        String query = "select EXISTS(SELECT * FROM Address WHERE City='" + city + "')";
        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);

            // Go first column
            result.next();
            return checkExistence(result);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public ArrayList<String[]> getCategories() throws DatabaseException{

        String query = "select * from category";

        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);

            // define an array list to contain result of query
            ArrayList<String[]> tableResult = new ArrayList<>();

            // Add table headers to array list
            tableResult.add(new String[]{ID_COL, CATEGORY_NAME_COL});
            // Add an empty row to have a space between headers and data rows
            tableResult.add(new String[]{"", ""});

            // Iterate over rows
            while (result.next()) {

                // Get values from columns
                String id = result.getString(ID_COL);
                String name = result.getString(CATEGORY_NAME_COL);

                // Insert values to array list as a new row
                tableResult.add(new String[]{id, name});
            }
            return tableResult;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(DEFAULT_ERROR_MESSAGE);
        }
    }

    public ArrayList<String[]> getBestUsers(String duration) throws DatabaseException{
        String query = "";
        String time_col = "";
        if(duration.equals("week")) {
            query = "select fullname,week(TransactionDate) as week " +
                    "from customer as c, transaction as t " +
                    "where c.ID = t.customerID " +
                    "group by week(TransactionDate) " +
                    "order by count(c.id) desc " +
                    "limit 10;";
            time_col = WEEK_COL;
        }else {
            query = "select fullname,month(TransactionDate) as month from customer as c, transaction as t where c.ID = t.customerID group by month(TransactionDate) order by count(c.id) desc limit 10; ";
            time_col = MONTH_COL;
        }

        try {
            // Execute query
            ResultSet result = statement.executeQuery(query);
            System.out.println(result);
            // define an array list to contain result of query
            ArrayList<String[]> tableResult = new ArrayList<>();

            // Add table headers to array list
            tableResult.add(new String[]{FULL_NAME_COL, time_col});
            // Add an empty row to have a space between headers and data rows
            tableResult.add(new String[]{"", ""});

            // Iterate over rows
            while (result.next()) {

                // Get values from columns
                String name = result.getString(FULL_NAME_COL);
                String time = result.getString(time_col);

                // Insert values to array list as a new row
                tableResult.add(new String[]{name, time});
            }
            return tableResult;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException(DEFAULT_ERROR_MESSAGE);
        }
    }

    public ArrayList<String[]> salesOfOneProductInMonth() throws DatabaseException {
        if (Application.isAdmin) {
            String query = "select productID, ProductName, month(date) as month, sum(ci.totalPrice) as sum from product as p,cartitem as ci, shoppingcart as sc, factor as f where p.id=ProductID and ci.ShoppingCartID = sc.ID and sc.FactorID = f.ID group by month(date) order by month(date) asc;";


            try {
                // Execute query
                ResultSet result = statement.executeQuery(query);
                System.out.println(result);
                // define an array list to contain result of query
                ArrayList<String[]> tableResult = new ArrayList<>();

                // Add table headers to array list
                tableResult.add(new String[]{PRODUCT_ID_COL, PRODUCT_NAME_COL, MONTH_COL, SUM_COL});
                // Add an empty row to have a space between headers and data rows
                tableResult.add(new String[]{"", "", "", ""});

                // Iterate over rows
                while (result.next()) {

                    // Get values from columns
                    String id = result.getString(PRODUCT_ID_COL);
                    String name = result.getString(PRODUCT_NAME_COL);
                    String month = result.getString(MONTH_COL);
                    String sum = result.getString(SUM_COL);

                    // Insert values to array list as a new row
                    tableResult.add(new String[]{id, name, month, sum});
                }
                return tableResult;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DatabaseException(DEFAULT_ERROR_MESSAGE);
            }
        } else {
            throw new DatabaseException(NOT_ADMIN_ERROR_MESSAGE);
        }
    }
    // endregion
}
