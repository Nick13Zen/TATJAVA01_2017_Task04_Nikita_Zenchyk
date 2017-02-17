package com.epam.task3.dao.impl;

import com.epam.task3.bean.News;
import com.epam.task3.dao.exception.DAOException;
import com.epam.task3.dao.util.ConnectionPool;
import com.epam.task3.dao.util.exception.ConnectionPoolException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.ArrayList;


/**
 *
 */
class FileReadNewsDAOTest {
    FileNewsDAO fileNewsDAO = new FileNewsDAO();
    ConnectionPool pool = ConnectionPool.getConnectionPool();
    Connection connection = null;

    @BeforeMethod
    public void setUp() {
        try {
            pool.initPooldata();
            connection = pool.takeConnection();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "positiveTestArgs")
    public Object[][] getNews() {
        return new Object[][]{
                {new News("movie", "long_book", "123")}
        };
    }

    @DataProvider(name = "negativeTestArgs")
    public Object[][] getIllegalArg() {
        return new Object[][]{
                {null}
        };
    }

    @Test(dataProvider = "positiveTestArgs")
    public void positiveTestForAddNews(News news) throws DAOException {
        fileNewsDAO.addNews(news);
    }

    @Test(dataProvider = "positiveTestArgs")
    public void positiveTestForFindNews(News news) throws DAOException {
        ArrayList<News> expected = new ArrayList<>();
        expected.add(news);
        fileNewsDAO.getNewsByAutor("123");
        Assert.assertEquals(fileNewsDAO.getNewsByAutor("123"), expected);
    }

    @Test(dataProvider = "negativeTestArgs", expectedExceptions = DAOException.class)
    public void negativeTestForAddNews(News news) throws DAOException {
        fileNewsDAO.addNews(news);
    }

    @Test(dataProvider = "negativeTestArgs", expectedExceptions = DAOException.class)
    public void negativeTestForFindNews() throws DAOException {
        fileNewsDAO.getNewsByAutor("null");
    }

}
