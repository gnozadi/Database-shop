package other;

import db.DatabaseException;

import java.util.ArrayList;

public interface ParamHandler {
    ArrayList<String[]> doAction(String s) throws DatabaseException;
}
