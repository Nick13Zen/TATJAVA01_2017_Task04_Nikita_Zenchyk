package com.epam.task3.dao;

import com.epam.task3.bean.News;
import com.epam.task3.dao.exception.DAOException;

import java.util.LinkedList;

/**
 *
 */
public interface NewsDAO {
    void addNews(News news) throws DAOException;
    LinkedList<News> getNewsByCategory(String searchparameter) throws DAOException;
    LinkedList<News> getNewsByAutor(String searchparameter) throws DAOException;
    LinkedList<News> getNewsByTitle(String searchparameter) throws DAOException;
    void init() throws DAOException ;
    void destroy() throws DAOException ;
}
