package com.example.translationtrainer;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EnglishTrainer implements Initializable {

    private final int countOfOutputWords = 10;
    private int score = 0;

    private ArrayList<Word> words; // значения из таблицы
    private ArrayList<Word> randomWords;
    private ArrayList<String> inputUser = new ArrayList<String>();

    private DBAdapter adapter = new DBAdapter();

    @FXML
    private VBox vectorPair;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //создаем или подключаем бд
        adapter.create_or_connection();

        //получаем значения из бд
        try {
            words = adapter.select_data();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //выводим рандомные английские слова, которые пользователю нужно перевести
        ObservableList<Node> childrenVBox = vectorPair.getChildren();
        randomWords = getRandomWords(countOfOutputWords);
        for (int i = 0; i < childrenVBox.size(); ++i)
        {
            HBox hbox = (HBox)childrenVBox.get(i);
            Label label = (Label)hbox.getChildren().getFirst();
            label.setText(randomWords.get(i).getEngWord());
        }
    }

    public void onCheckButtonClick() throws IOException, SQLException {
        ObservableList<Node> childrenVBox = vectorPair.getChildren();
        for (int i = 0; i < childrenVBox.size(); ++i)
        {
            HBox hbox = (HBox)childrenVBox.get(i);
            TextField textField = (TextField) hbox.getChildren().get(1);
            //сравниваем ввод пользователя с правильным переводом, добавляем индикатор и сохраняем
            if (!textField.getText().equalsIgnoreCase(randomWords.get(i).getRusWord()))
                inputUser.add(textField.getText() + "/0");
            else
            {
                inputUser.add(textField.getText() + "/1");
                score++;
            }
        }
        closeAndOpenResult();
    }

    private void closeAndOpenResult() throws IOException, SQLException {
        Stage stage = (Stage) (vectorPair.getScene().getWindow());
        stage.hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("result-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 400);
        stage = new Stage();
        stage.setTitle("Result");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        Result result = loader.getController();
        result.showResults(randomWords, inputUser, score);
    }

    private ArrayList<Word> getRandomWords(int count)
    {
        ArrayList<Word> randomWords = new ArrayList<Word>();
        Random random = new Random();

        while (randomWords.size() < count)
        {
            int randomIndex = random.nextInt(words.size());
            Word randomWord = words.get(randomIndex);
            if (!randomWords.contains(randomWord))
                randomWords.add(randomWord);
        }
        return randomWords;
    }
}