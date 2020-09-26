package com.epam.anatolii.ageev.constants.sql;

public class SqlQuery {
    public static final String SQL_INSERT_USER = "INSERT INTO computer_shop.users (login, first_name, last_name, email, password) VALUES(?,?,?,?,?)";
    public static final String SQL_GEY_ONE = "SELECT id, login, first_name, last_name, email, password FROM computer_shop.users WHERE login = ?";
    public static final String SQL_DELETE_USER = "DELETE FROM computer_shop.users WHERE login = ?";
    public static final String SQL_GET_ALL = "SELECT id, login, first_name, last_name, email, password FROM computer_shop.users";
}
