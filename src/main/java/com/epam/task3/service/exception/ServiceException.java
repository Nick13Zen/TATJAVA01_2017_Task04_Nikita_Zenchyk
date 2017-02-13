package com.epam.task3.service.exception;

/**
 *
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(Exception e) {
        super(e);
    }
}
