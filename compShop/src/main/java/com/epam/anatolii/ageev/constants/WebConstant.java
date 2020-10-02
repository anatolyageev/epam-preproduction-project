package com.epam.anatolii.ageev.constants;

public class WebConstant {
    //path
    public static final String MAIN_PAGE = "index.jsp";

    public static final String USER_SERVICE = "userService";
    public static final String PRODUCT_SERVICE = "productService";
    public static final String CAPTCHA_STRATEGY = "captchaStrategy";

    //Register constants

    public static final String USER_ID = "userId";
    public static final String USER_NAME = "name";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_CAPTCHA = "user_captcha";
    public static final String REGISTER_ERROR = "error_message";
    public static final String LOGIN_ERROR = "error_message";
    public static final String LOGIN_USER = "loginUser";
    public static final String USER_AVATAR = "user_avatar";

    //Captcha

    public static final String CAPTCHA_VALUE = "captcha_value";
    public static final String CAPTCHA_TIME_CREATED = "captcha_created_time";
    public static final String CAPTCHA_TIME_OUT = "captchaTimeout";
    public static final long CAPTCHA_TIME_OUT_JOB_TIMEOUT = 10000;

    //context constance
    public static final String COOKIE_CONST = "cookie";
    public static final String FIELD_CONST = "field";
    public static final String SESSION_CONST = "session";
    public static final String CAPTCHA_TYPE = "captchaType";
    public static final String HIDDEN_FIELD = "hidden_field";

    //Avatar

    public static final String AVATAR_PNG_EXTENSION = ".png";
    public static final String AVATAR_PNG_FORMAT_NAME = "png";
    public static final String SERVER_USER_DIR = "catalina.home";

    //Product
    public static final String PRODUCT_LIST = "productList";
    public static final String PRODUCT_FILTER_BEEN = "productFilterBean";
    public static final String PRODUCT_FILTER_FORM_NAME = "productName";
    public static final String PRODUCT_FILTER_FORM_CATEGORY = "category";
    public static final String PRODUCT_FILTER_FORM_PRODUCER = "producer";
    public static final String PRODUCT_FILTER_FORM_MIN_PRICE = "minPrice";
    public static final String PRODUCT_FILTER_FORM_MAX_PRICE = "maxPrice";
    public static final String PRODUCT_FILTER_FORM_FIELD_SORT = "fieldSort";
    public static final String PRODUCT_FILTER_FORM_SORT_DIRECTION = "sortDirection";
    public static final String PRODUCT_FILTER_FORM_PRODUCTS_PER_PAGE = "productsPerPage";
    public static final String PRODUCT_FILTER_FORM_PRODUCTS_OFFSET = "offset";
    public static final Integer PRODUCT_FILTER_FORM_PRODUCTS_DEFAULT_OFFSET = 0;
    public static final Integer PRODUCT_FILTER_FORM_PRODUCTS_PER_PAGE_DEFAULT = 10;
    public static final String NUMBER_OF_PAGES = "noOfPages";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String HREF_PAGINATION = "hrefForPagination";

    public static final String PRODUCERS_LIST = "producersList";
    public static final String CATEGORIES_LIST = "categoriesList";

    //jsp constance
    public static final Integer INT_ONE = 1;
    public static final Integer INT_TWO = 2;

}
