package com.example.translationtrainer;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

//класс, описывающий значение таблицы
public class Word {
    private SimpleIntegerProperty id;
    private SimpleStringProperty engWord;
    private  SimpleStringProperty rusWord;

    public Word(int id, String engWord, String rusWord) {
        this.id = new SimpleIntegerProperty(id);
        this.engWord = new SimpleStringProperty(engWord);
        this.rusWord = new SimpleStringProperty(rusWord);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int val_id) {
        id.set(val_id);
    }

    public String getEngWord() {
        return engWord.get();
    }

    public void setEngWord(String val_engWord) {
        engWord.set(val_engWord);
    }

    public String getRusWord() {
        return rusWord.get();
    }

    public void setRusWord(String val_rusWord) {
        rusWord.set(val_rusWord);
    }
}
