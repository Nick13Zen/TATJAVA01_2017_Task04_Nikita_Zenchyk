package com.epam.task3.dao.impl;

import com.epam.task3.bean.News;
import com.epam.task3.controller.command.impl.AddNews;
import com.epam.task3.dao.NewsDAO;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.util.ConnectionPool;
import com.epam.task3.dao.util.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;

/**
 * Class to work with data files.
 */
public class FileNewsDAO implements NewsDAO {

    private static final Logger logger = LogManager.getLogger(AddNews.class.getName());

    private ConnectionPool pool = ConnectionPool.getConnectionPool();

    private final String INSERT_QUERY = "INSERT INTO news (title, category, author) VALUES (?, ?, ?)";
    private final String SELECT_TITLE_QUERY = "SELECT * FROM news WHERE title = ?";
    private final String SELECT_CATEGORY_QUERY = "SELECT * FROM news WHERE category = ?";
    private final String SELECT_AUTHOR_QUERY = "SELECT * FROM news WHERE author = ?";

    /**
     * Method writes current news, got as argument, to database
     *
     * @param news current news to write
     * @throws DAOException if there are exceptions in DAO
     */
    @Override
    public void addNews(News news) throws DAOException {
        PreparedStatement preparedStatement = null;
        try (Connection connection = pool.takeConnection()) {
            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getCategory());
            preparedStatement.setString(3, news.getAuthor());
            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public LinkedList<News> getNewsByCategory(String searchparameter) throws DAOException {
        return getNews(searchparameter, SELECT_CATEGORY_QUERY);
    }

    @Override
    public LinkedList<News> getNewsByAutor(String searchparameter) throws DAOException {
        return getNews(searchparameter, SELECT_AUTHOR_QUERY);
    }

    @Override
    public LinkedList<News> getNewsByTitle(String searchparameter) throws DAOException {
        return getNews(searchparameter, SELECT_TITLE_QUERY);
    }

    @Override
    public void init() throws DAOException {
        try {
            pool.initPooldata();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void destroy() {
        pool.dispose();
    }

    /**
     * Method to get news from database
     *
     * @return Arraylist of news objects
     * @throws DAOException
     */

    private LinkedList<News> getNews(String searchparam, String query) throws DAOException {
        LinkedList<News> findNews = new LinkedList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = pool.takeConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, searchparam);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News(resultSet.getString("Category"),
                        resultSet.getString("Title"),
                        resultSet.getString("Author"));
                findNews.add(news);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DAOException(e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return findNews;
    }
}