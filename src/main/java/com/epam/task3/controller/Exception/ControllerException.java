package com.epam.task3.controller.exception;

/**
 *
 */
public class ControllerException extends Exception {
    public ControllerException() {
        super();
    }

    public ControllerException(String message) {
        super(message);
    }

    public ControllerException(Exception e) {
        super(e);
    }
}
