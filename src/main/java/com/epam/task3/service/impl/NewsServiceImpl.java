package com.epam.task3.service.impl;

import com.epam.task3.bean.News;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.factory.DAOFactory;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;

import java.util.ArrayList;

/**
 *
 */
public class NewsServiceImpl implements NewsService {
    DAOFactory daoFactory = DAOFactory.getInstance();
    NewsDAO newsDAO;

    @Override
    public void addNews(News news) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            NewsDAO newsDAO = daoFactory.getNewDAO();
            if (validator(news)) {
                throw new ServiceException();
            } else {
                newsDAO.addNews(news);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<News> getNewsByTitle(String searchparameter) throws ServiceException {
        NewsDAO newsDAO;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            newsDAO = daoFactory.getNewDAO();
            return newsDAO.getNewsByTitle(searchparameter);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<News> getNewsByCategory(String searchparameter) throws ServiceException {
        NewsDAO newsDAO;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            newsDAO = daoFactory.getNewDAO();
            return newsDAO.getNewsByCategory(searchparameter);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<News> getNewsByAutor(String searchparameter) throws ServiceException {
        try {
            return newsDAO.getNewsByAutor(searchparameter);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createConnection() throws ServiceException {
        newsDAO = daoFactory.getNewDAO();
        try {
            newsDAO.createConnection();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void destroyCeonnection() throws ServiceException {
        try {
            newsDAO.destroyConnection();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    private boolean validator(News news) {
        if (news.getCategory().isEmpty()
                || news.getAuthor().isEmpty()
                || news.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
