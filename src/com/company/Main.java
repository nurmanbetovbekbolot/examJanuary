package com.company;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DB db = new DB();
         db.createNews("Fire","In Australia thwrthnwsrynsrfndfgn dfgh dfg n");
        db.getNews();
         db.deleteNews(3);
        db.getNews();
         db.modifyNews(2,"Fire","Now everything is ok! ");
          News news = new News("Second","aewrgvbaetbaerfb sfgrb sfgbsfrgbrtgergergefgbvsrgbsgtf");
          db.getNews();
          db.createNews(news);
        System.out.println(db.getNewsByID(6));
    }
}