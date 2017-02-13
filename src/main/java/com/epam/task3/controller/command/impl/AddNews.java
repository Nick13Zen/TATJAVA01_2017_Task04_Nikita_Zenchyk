package com.epam.task3.controller.command.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.NewsCategory;
import com.epam.task3.controller.command.Command;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class AddNews implements Command {

    final Logger logger = LogManager.getLogger(AddNews.class.getName());

    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        String response = null;
        try {
            response = checkCorrectCategoryOfNews(request);
            newsService.addNews(getParamOfNews(request));
        } catch (IllegalArgumentException e) {
            logger.error(e);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return response;
    }

    /**
     * @param request
     * @return
     */
    private News getParamOfNews(String request) {
        request = request.substring(request.indexOf(' ') + 1, request.length());
        String[] paramNews = request.split(",");
        News news;
        if (paramNews.length < 3 || paramNews.length > 3) {
            throw new IllegalArgumentException("Entered incorrect number of parameters!");
        } else {
            news = new News(paramNews[0], paramNews[1], paramNews[2]);
        }
        return news;
    }

    private String checkCorrectCategoryOfNews(String request) {
        String category = request.substring(request.indexOf(' ') + 1, request.indexOf(','));
        String response = null;
        try {
            if (category.toUpperCase().equals(NewsCategory.valueOf(category.toUpperCase()).toString())) {
                response = "News added!";
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error when you add news!");
        }
        return response;
    }
}
