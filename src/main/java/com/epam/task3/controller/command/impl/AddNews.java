package com.epam.task3.controller.command.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.NewsCategory;
import com.epam.task3.controller.command.Command;
import com.epam.task3.controller.exception.ControllerException;
import com.epam.task3.controller.exception.ParametersException;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;
import com.epam.task3.service.factory.ServiceFactory;
import com.sun.jndi.ldap.pool.PooledConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AddNews implements Command {

    private static final Logger logger = LogManager.getLogger(AddNews.class.getName());

    @Override
    public String execute(String request) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        String response;
        try {
            response = checkCorrectCategoryOfNews(request);
            newsService.addNews(getParamOfNews(request));
        } catch (ParametersException e) {
            logger.error(e);
            response = "Error adding news";
        } catch (ServiceException e) {
            logger.error(e);
            response = "Error adding news.";
        }
        return response;
    }

    private News getParamOfNews(String request) throws ParametersException {
        String[] paramNews = request.split(",");
        News news;
        if (paramNews.length < 3 || paramNews.length > 3) {
            throw new ParametersException();
        } else {
            news = new News(paramNews[0], paramNews[1], paramNews[2]);
        }
        return news;
    }

    private String checkCorrectCategoryOfNews(String request) throws ParametersException {
        String category =request.substring(request.indexOf(' ') + 1, request.indexOf(','));
        String response;
        if (category.toUpperCase().equals(NewsCategory.valueOf(category.toUpperCase()).toString())) {
            response = "News added!";
        } else {
            response = "Unknown news category.";
            throw new ParametersException();
        }
        return response;
    }
}
