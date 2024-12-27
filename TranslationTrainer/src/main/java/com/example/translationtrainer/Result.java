package com.example.translationtrainer;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.util.ArrayList;

public class Result
{
    private DBAdapter adapter = new DBAdapter();

    @FXML
    private VBox vectorPair;

    public void showResults(ArrayList<Word> randomWords, ArrayList<String> inputUser, int score) throws SQLException
    {
        ObservableList<Node> childrenVBox = vectorPair.getChildren();
        for (int i = 0; i < childrenVBox.size(); ++i)
        {
            HBox hbox = (HBox)childrenVBox.get(i);

            //отображаем слово на английском
            Label labelEng = (Label)hbox.getChildren().get(0);
            labelEng.setText(randomWords.get(i).getEngWord());

            //отображаем ввод пользователя и придаём ему цвет
            Label labelInputUser = (Label)hbox.getChildren().get(1);
            labelInputUser.setText(wordParser(inputUser.get(i)));
            labelInputUser.setTextFill(Paint.valueOf(getAnswerLabelColor(inputUser.get(i))));

            //отображаем правильный перевод
            Label labelRus = (Label)hbox.getChildren().get(2);
            labelRus.setText(randomWords.get(i).getRusWord());
        }
        //Записываем результат пользователя
        adapter.create_or_connection();
        adapter.insert_score(score);
    }

    private String getAnswerLabelColor(String str)
    {
        char i = str.charAt(str.length() - 1);
        if (i == '1') return "green";
        else return "red";
    }

    private String wordParser(String str)
    {
        return str.substring(0,str.length() - 2);
    }
}