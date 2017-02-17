package com.epam.task3.controller.command.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Nikita_Zenchyk on 2/13/2017.
 */
public class FindNewsByCategory implements Command {

    private static final Logger logger = LogManager.getLogger(AddNews.class.getName());

    @Override
    public String execute(String request) {
        String response;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        try {
            LinkedList<News> newsList = newsService.getNewsByCategory(request);
            StringBuilder stringBuilder = new StringBuilder();
            for (News news : newsList) {
                stringBuilder.append(" " +
                        news.getCategory() + " " +
                        news.getTitle() + " " +
                        news.getAuthor() + "\n");

            }
            response = stringBuilder.toString();
            if (response.isEmpty()) {
                response = "Nothing was found. ";
            }
        } catch (ServiceException e) {
            logger.error(e);
            response = "Error getting news.";

        }
        return response;
    }
}
