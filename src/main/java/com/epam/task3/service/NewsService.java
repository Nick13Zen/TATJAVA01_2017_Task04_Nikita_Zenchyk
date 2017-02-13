package com.epam.task3.service;

import com.epam.task3.bean.News;
import com.epam.task3.service.exception.ServiceException;

import java.util.ArrayList;

/**
 *
 */
public interface NewsService {
    void addNews(News news) throws ServiceException;
    ArrayList<News> getNewsByTitle(String searchparameter) throws ServiceException;
    ArrayList<News> getNewsByCategory(String searchparameter) throws ServiceException;
    ArrayList<News> getNewsByAutor(String searchparameter) throws ServiceException;
    void createConnection()throws ServiceException;
    void destroyCeonnection()throws ServiceException;
}
