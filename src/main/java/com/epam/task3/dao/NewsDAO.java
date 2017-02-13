package com.epam.task3.dao;

import com.epam.task3.bean.News;
import com.epam.task3.dao.exception.DAOException;

import java.util.ArrayList;

/**
 *
 */
public interface NewsDAO {
    void addNews(News news) throws DAOException;
    ArrayList<News> getNewsByCategory(String searchparameter) throws DAOException;
    ArrayList<News> getNewsByAutor(String searchparameter) throws DAOException;
    ArrayList<News> getNewsByTitle(String searchparameter) throws DAOException;
    void createConnection() throws DAOException ;
    void destroyConnection() throws DAOException ;
}
