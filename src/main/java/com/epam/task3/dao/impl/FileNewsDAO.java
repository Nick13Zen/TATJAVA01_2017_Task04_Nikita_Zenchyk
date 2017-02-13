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
import java.util.ArrayList;

/**
 * Class to work with data files.
 */
public class FileNewsDAO implements NewsDAO {

    final Logger logger = LogManager.getLogger(AddNews.class.getName());

    private ConnectionPool pool = ConnectionPool.getConnectionPool();
    private Connection connection = null;

    private final String DB_NAME = "news";
    private final String INSERT_QUERY = "INSERT INTO " + DB_NAME + " (title, category, author) VALUES (?, ?, ?)";
    private final String SELECT_TITLE_QUERY = "SELECT * FROM " + DB_NAME + " WHERE title = ?";
    private final String SELECT_CATEGORY_QUERY = "SELECT * FROM " + DB_NAME + " WHERE category = ?";
    private final String SELECT_AUTHOR_QUERY = "SELECT * FROM " + DB_NAME + " WHERE author = ?";

    private ArrayList<News> findNews;

    /**
     * Method writes current news, got as argument, to database
     *
     * @param news current news to write
     * @throws DAOException if there are exceptions in DAO
     */
    @Override
    public void addNews(News news) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getCategory());
            preparedStatement.setString(3, news.getAuthor());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public ArrayList<News> getNewsByCategory(String searchparameter) throws DAOException {
        return getNews(searchparameter, SELECT_CATEGORY_QUERY);
    }

    @Override
    public ArrayList<News> getNewsByAutor(String searchparameter) throws DAOException {
        return getNews(searchparameter, SELECT_AUTHOR_QUERY);
    }

    @Override
    public ArrayList<News> getNewsByTitle(String searchparameter) throws DAOException {
        return getNews(searchparameter, SELECT_TITLE_QUERY);
    }

    @Override
    public void createConnection() throws DAOException {
        try {
            pool.initPooldata();
            connection = pool.takeConnection();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void destroyConnection() throws DAOException {
        pool.dispose();
    }

    /**
     * Method to get news from database
     *
     * @return Arraylist of news objects
     * @throws DAOException
     */

    private ArrayList<News> getNews(String searchparam, String quety) throws DAOException {
        findNews = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(quety);
            preparedStatement.setString(1, searchparam);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                News news = new News(resultSet.getString("Category"),
                        resultSet.getString("Title"),
                        resultSet.getString("Author"));
                findNews.add(news);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return findNews;
    }
}