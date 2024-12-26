package com.example.translationtrainer;

import java.sql.*;
import java.util.ArrayList;

public class DBAdapter {
    Connection con;

    void create_or_connection() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:translationTrainer.sqlite");

            Statement stmt = con.createStatement();

            String sqlCreateTableWords = """
                    create table if not exists words
                    (
                        id           integer primary key autoincrement,
                        engWord      text,
                        rusWord      text
                    );""";

            String sqlCreateTableUserSessions = """
                    create table if not exists userSessions
                    (
                        id           integer primary key autoincrement,
                        score        integer
                    );""";

            String sqlFill = """
                    INSERT INTO words (engWord, rusWord)
                    SELECT * FROM (VALUES
                    ('There', 'там'),
                    ('Street', 'улица'),
                    ('River', 'река'),
                    ('Breakfast', 'завтрак'),
                    ('Dinner', 'ужин'),
                    ('Meat', 'мясо'),
                    ('Bread', 'хлеб'),
                    ('Milk', 'молоко'),
                    ('Tea', 'чай'),
                    ('Water', 'вода'),
                    ('Advice', 'совет'),
                    ('Any', 'любой'),
                    ('Clothes', 'одежда'),
                    ('Color', 'цвет'),
                    ('Paper', 'бумага'),
                    ('School', 'школа'),
                    ('Teacher', 'учитель'),
                    ('University', 'университет'),
                    ('Job', 'работа'),
                    ('Flower', 'цветок'),
                    ('Grass', 'трава'))
                    WHERE NOT EXISTS (SELECT 1 FROM words);""";

            stmt.execute(sqlCreateTableWords);
            stmt.execute(sqlFill);
            stmt.execute(sqlCreateTableUserSessions);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void insert_score(int score) throws SQLException {
        String sql = "INSERT INTO userSessions(score) VALUES(" + score + ")";
        Statement stmt = con.createStatement();
        stmt.execute(sql);
        stmt.close();
        System.out.println("Inserted score");
    }

    ArrayList<Word> select_data() throws SQLException {
        ArrayList<Word> words = new ArrayList<Word>();

        String sql = "SELECT *  FROM words";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String engWord = rs.getString("engWord");
            String rusWord = rs.getString("rusWord");
            words.add(new Word(id, engWord, rusWord));
        }
        return words;
    }
}
