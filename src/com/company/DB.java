package com.company;

import java.sql.*;

public class DB {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String news = "postgres";
    private final String password = "123";

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, news, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public void createNews(String title, String newsText) {
        String SQL = "INSERT INTO news(title,newsText,publicationTime) values(?,?,now()) ";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, title);
            stmt.setString(2, newsText);
            stmt.executeUpdate();
            System.out.println("New post edited");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean createNews(News news) {
        String SQL = "INSERT INTO news(title,newsText,publicationTime) values(?,?,now()) ";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getNewsText());
            stmt.executeUpdate();
            System.out.println("New post edited: " + news);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void getNews() {
        String SQL = "SELECT title,newsText FROM news ";
        try (
                Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getString("title")
                        + "\t" + rs.getString("newsText"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public News getNewsByID(int id) {
        String SQL = "select * from news where id = ?";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setNewsText(rs.getString("newsText"));
                    return news;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteNews(int id) {
        String SQL = "DELETE FROM news WHERE id=? ";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Post deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifyNews(int id, String title, String newsText) {
        String SQL = "UPDATE news SET title=?,newsText=? WHERE  id=? ";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setString(1, title);
            stmt.setString(2, newsText);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("Post updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}