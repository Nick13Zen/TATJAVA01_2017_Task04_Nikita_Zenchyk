package com.epam.task3.service.impl;

import com.epam.task3.bean.News;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.factory.DAOFactory;
import com.epam.task3.service.NewsService;
import com.epam.task3.service.exception.ServiceException;

import java.util.LinkedList;

/**
 *
 */
public class NewsServiceImpl implements NewsService {
    private static final DAOFactory daoFactory = DAOFactory.getInstance();
    private static final NewsDAO newsDAO = daoFactory.getNewDAO();

    @Override
    public void addNews(News news) throws ServiceException {
        try {
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
    public LinkedList<News> getNewsByTitle(String searchparameter) throws ServiceException {
        try {
            return newsDAO.getNewsByTitle(searchparameter);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public LinkedList<News> getNewsByCategory(String searchparameter) throws ServiceException {
        try {
            return newsDAO.getNewsByCategory(searchparameter);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public LinkedList<News> getNewsByAutor(String searchparameter) throws ServiceException {
        try {
            return newsDAO.getNewsByAutor(searchparameter);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void init() throws ServiceException {
        try {
            newsDAO.init();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void destroy() throws ServiceException {
        try {
            newsDAO.destroy();
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
