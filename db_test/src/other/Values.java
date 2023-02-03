package other;

public interface Values {
    // region Admin
    String ADMIN_COL = "admin";
    String ADMIN_VALUE = "1";
    String NORMAL_USER_VALUE = "0";
    // endregion

    // region Order
    String ID_COL = "ID";
    String TOTAL_PRICE_COL = "TotalPrice";
    String TOTAL_DISCOUNT_COL = "TotalDiscount";
    String DATE_COL = "Date";
    // endregion

    //region Category
    String CATEGORY_NAME_COL ="CategoryName";
    //endregion

    // region Product
    String PRICE_COL = "Price";
    String BRAND_COL = "Brand";
    String PRODUCT_NAME_COL = "ProductName";
    String DISCOUNT_COL = "Discount";
    String PRODUCT_ID_COL = "productID";
    String SUM_COL = "sum";
    //endregion

    // region product review
    String TITLE_COL = "Title";
    String RATING_COL = "Rating";
    // endregion

    // region user
    String FULL_NAME_COL = "FullName";
    String WEEK_COL = "week";
    String MONTH_COL = "month";
    // endregion

    // region user
    String PROVIDER_NAME_COL = "PName";
    // endregion

    // region address
    String CITY_COL = "City";
    String COUNTRY_COL = "Country";
    String PHONE_COL = "Phone";
    String POSTAL_CODE_COL = "PostalCode";
    // endregion

    // region Exist
    int EXIST_VALUE = 1;
    int NOT_EXIST_VALUE = 0;
    //endregion

    //region Messages
    String Fill_INPUT = "Please fill input!";
    String Fill_INPUTS = "Please fill inputs!";
    String WAITING = "Please wait...";
    String PASS_WITH_REPEAT_NOT_MATCH = "Password and its repeat doesn't match!";
    String PASSWORD_8_CHARACTER = "Password should be more than 7 characters!";
    String INVALID_EMAIL = "Invalid email!";
    String NO_RECORD = "No record!";

    // region Success messages
    String USER_LOGGED = "User logged in successfully!";
    String USER_SIGNED_UP = "User signed up in successfully!";
    String PASSWORD_CHANGED = "Password changed successfully!";
    String USERNAME_CHANGED = "Username changed successfully!";
    // endregion

    // region Error messages
    String INVALID_USERNAME = "Username does not exits!";
    String REPEATED_USERNAME = "Username exits!";
    String USER_PASS_NOT_MATCH = "Username and password doesn't match!";
    String INVALID_PASSWORD = "Password doesn't match!";

    String INVALID_PRODUCT = "Product doesn't exist!";
    String INVALID_CITY = "City doesn't exist!";

    String DEFAULT_ERROR_MESSAGE = "Something is wrong!";
    String NOT_ADMIN_ERROR_MESSAGE = "This feature is available for admin only!";
    // endregion

    // endregion
}
