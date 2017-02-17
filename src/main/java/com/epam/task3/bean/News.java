package com.epam.task3.bean;


/**
 * Class containing news data
 */
public class News {
    private String category;
    private String title;
    private String author;

    public News(){

    }

    public News(String category, String title, String author) {
        this.category = category;
        this.title = title;
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
