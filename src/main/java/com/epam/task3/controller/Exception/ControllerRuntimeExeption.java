package com.epam.task3.controller.exception;

/**
 * Created by Nikita_Zenchyk on 2/17/2017.
 */
public class ControllerRuntimeExeption extends RuntimeException {

    public ControllerRuntimeExeption() {
        super();
    }

    public ControllerRuntimeExeption(String message) {
        super(message);
    }

    public ControllerRuntimeExeption(Exception e) {
        super(e);
    }
}
