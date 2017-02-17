package com.epam.task3.start;

import com.epam.task3.controller.Controller;


public class Main {
    private final static String ADD_NEWS = "add_news-movie,2323,jkgf";
    private final static String FIND_NEWS = "find_news_by_category-movie";

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.init();
        System.out.println(controller.executeTask(ADD_NEWS));
        System.out.println(controller.executeTask(FIND_NEWS));
        controller.destroy();
    }
}