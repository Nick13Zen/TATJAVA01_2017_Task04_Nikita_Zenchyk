package com.epam.task3.dao.exception;

/**
 *
 */
public class DAOException extends Exception {
    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }
    public DAOException(Exception e) {
        super(e);
    }
}
