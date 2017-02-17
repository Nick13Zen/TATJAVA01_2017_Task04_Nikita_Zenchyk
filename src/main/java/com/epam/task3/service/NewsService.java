package com.epam.task3.service;

import com.epam.task3.bean.News;
import com.epam.task3.service.exception.ServiceException;

import java.util.LinkedList;

/**
 *
 */
public interface NewsService {
    void addNews(News news) throws ServiceException;
    LinkedList<News> getNewsByTitle(String searchparameter) throws ServiceException;
    LinkedList<News> getNewsByCategory(String searchparameter) throws ServiceException;
    LinkedList<News> getNewsByAutor(String searchparameter) throws ServiceException;
    void init()throws ServiceException;
    void destroy()throws ServiceException;

}
