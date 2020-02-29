package com.example.restservice;

import org.springframework.beans.factory.annotation.Value;

/**
 * Represents a data transfer object
 */

public class GameInfo {
    private Integer duration;
    private Boolean random;
    private Integer id;
    private String token;
    private String board;
    private String word;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void print() {
        System.out.println("duration: " + duration + " isRandom: " + random + " id: " + id + " token: " + token + " board: " + board + " word: " + word);
    }
}
