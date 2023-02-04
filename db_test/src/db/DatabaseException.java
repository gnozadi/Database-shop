package db;

import java.sql.SQLException;

import static other.Values.*;

public class DatabaseException extends SQLException {

    public DatabaseException(String message) {
        super(message);
    }
}

class NotFoundUser extends DatabaseException {

    public NotFoundUser() {
        super(INVALID_USERNAME);
    }
}

class RepeatedUser extends DatabaseException {

    public RepeatedUser() {
        super(REPEATED_USERNAME);
    }
}

class MismatchUserPass extends DatabaseException{

    public MismatchUserPass() {
        super(USER_PASS_NOT_MATCH);
    }
}

class NotFoundProduct extends DatabaseException{

    public NotFoundProduct() {        super(INVALID_PRODUCT); }
}

class NotFoundCity extends DatabaseException{
    public NotFoundCity() {
        super(INVALID_CITY);
    }
}




