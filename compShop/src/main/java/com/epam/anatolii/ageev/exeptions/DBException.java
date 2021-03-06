package com.epam.anatolii.ageev.exeptions;

/**
 * An exception that provides information on a database access error.
 *
 * @author Anatoliy Ageev
 *
 */
public class DBException extends AppException {

    private static final long serialVersionUID = -3550446897536410392L;

    public DBException() {
        super();
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

}