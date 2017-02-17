package com.epam.task3.controller.exception;

/**
 * Created by Nikita_Zenchyk on 2/17/2017.
 */
public class ParametersException extends Exception {
    public ParametersException() {
        super();
    }

    public ParametersException(String message) {
        super(message);
    }

    public ParametersException(Exception e) {
        super(e);
    }
}
