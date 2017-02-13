package com.epam.task3;

import com.epam.task3.controller.Controller;

/**
 *
 */
public class Main {
    private final static String ADD_NEWS = "add_new-movie,long,jkgf";
    private final static String FIND_NEWS = "find_news_by_title-short";

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.createConnection();
        //controller.executeTask(ADD_NEWS);
        System.out.println(controller.executeTask(FIND_NEWS));
        controller.destroyConnection();
    }
}