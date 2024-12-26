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
import java.util.Objects;

public class Result
{
    private DBAdapter adapter = new DBAdapter();

    private int score = 10;

    @FXML
    private VBox vectorPair;

    public void addData(ArrayList<Word> randomWords, ArrayList<String> inputUser) throws SQLException {
        ObservableList<Node> childrenVBox = vectorPair.getChildren();
        for (int i = 0; i < childrenVBox.size(); ++i)
        {
            HBox hbox = (HBox)childrenVBox.get(i);
            Label labelEng = (Label)hbox.getChildren().get(0);
            labelEng.setText(randomWords.get(i).getEngWord());

            Label labelInputUser = (Label)hbox.getChildren().get(1);
            labelInputUser.setText(inputUser.get(i));
            labelInputUser.setTextFill(Paint.valueOf("green"));
            if (!Objects.equals(inputUser.get(i), randomWords.get(i).getRusWord()))
            {
                labelInputUser.setTextFill(Paint.valueOf("red"));
                score--;
            }

            Label labelRus = (Label)hbox.getChildren().get(2);
            labelRus.setText(randomWords.get(i).getRusWord());
        }
        //Записываем результат пользователя
        adapter.create_or_connection();
        adapter.insert_score(score);
    }
}