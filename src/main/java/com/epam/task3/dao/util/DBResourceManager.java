package com.epam.task3.dao.util;

import java.util.ResourceBundle;

/**
 * Created by Nikita_Zenchyk on 2/9/2017.
 */
public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private ResourceBundle bundle
            = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
