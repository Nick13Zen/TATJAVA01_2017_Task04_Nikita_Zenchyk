package com.epam.task3.dao.util.exception;

/**
 * Created by Nikita_Zenchyk on 2/9/2017.
 */
public class ConnectionPoolException extends Exception {
    private static final long serialVersionUID = 1L;

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
