package com.epam.anatolii.ageev.constants.sql;

public class SqlQuery {
    public static final String SQL_INSERT_USER = "INSERT INTO computer_shop.users (login, first_name, last_name, email, password) VALUES(?,?,?,?,?)";
    public static final String SQL_GEY_ONE = "SELECT id, login, first_name, last_name, email, password FROM computer_shop.users WHERE login = ?";
    public static final String SQL_DELETE_USER = "DELETE FROM computer_shop.users WHERE login = ?";
    public static final String SQL_GET_ALL = "SELECT id, login, first_name, last_name, email, password FROM computer_shop.users";

    public static final String SQL_PRODUCT_GET_ALL = "SELECT pr.product_id, category_name, producer_name, name, short_description, price, image, meta_title  FROM computer_shop.products pr left join computer_shop.categories c ON pr.category_id = c.id left join computer_shop.producers p ON pr.producer_id = p.id";
    public static final String SQL_PRODUCT_GET_COUNT = "SELECT COUNT(*) count FROM computer_shop.products pr left join computer_shop.categories c ON pr.category_id = c.id left join computer_shop.producers p ON pr.producer_id = p.id";
    public static final String SQL_GET_ONE_PRODUCT = "SELECT pr.product_id, category_name, producer_name, name, short_description, price, image, meta_title FROM computer_shop.products pr left join computer_shop.categories c ON pr.category_id = c.id left join computer_shop.producers p ON pr.producer_id = p.id WHERE pr.product_id = ?";

    public static final String SQL_GET_ALL_PRODUCERS = "SELECT computer_shop.producers.producer_name FROM computer_shop.producers";
    public static final String SQL_GET_ALL_CATEGORIES = "SELECT computer_shop.categories.category_name FROM computer_shop.categories";
}
