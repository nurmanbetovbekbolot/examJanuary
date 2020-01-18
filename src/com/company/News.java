package com.company;

public class News {
    private int id;
    private String title;
    private String newsText;

    public News( String title, String newsText) {
        this.title = title;
        this.newsText = newsText;
    }

    public News() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", newsText='" + newsText + '\'' +
                '}';
    }
}
