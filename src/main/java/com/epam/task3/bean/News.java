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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        News news = (News) object;

        if (!category.equals(news.category)) return false;
        if (!title.equals(news.title)) return false;
        return author.equals(news.author);
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
