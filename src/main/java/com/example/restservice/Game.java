package com.example.restservice;

import com.example.restservice.Exceptions.InvalidInputException;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private Board board;
    private Set<String> usedWords = new HashSet<>();
    private Solver solver = SpringContext.getBean(Solver.class);

    private String token;
    private int id;
    private int duration; //change this to proper class
    private int timeLeft = -1;
    private int points = 0;
    private boolean started = false;
    private boolean isRandom; //recording purposes
    private long startTime;

    public boolean makeMove(String input) {

        updateTime();
        if (timeLeft <= 0) {
            return false;
        }

        String word = input.toUpperCase();

        if (!Dictionary.isValidDictionaryWord(word)) {
            return false;
        }

        if (usedWords.contains(word)) {
            return false;
        }

        if (solver.doesWordExistOnBoard(board, word)) {
            points += word.length();
            usedWords.add(word);
        } else {
            return false;
        }

        return true;
    }

    public String printBoard() {
        return this.board.print();
    }

    public int getTimeLeft() {
        return this.timeLeft;
    }

    public int getDuration() {
        return this.duration;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isRandom() {
        return isRandom;
    }

    public void setRandom(boolean random) {
        isRandom = random;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getPoints() {
        return this.points;
    }

    public boolean hasStarted() {
        return this.started;
    }

    public String getToken() {
        return this.token;
    }

    public boolean checkTokenValidity(String token) {
        return token.equals(this.token);
    }

    public void updateTime() {
        long timeNow = System.currentTimeMillis();
        long difference = timeNow - startTime;
        this.timeLeft = Math.max(duration - (int) difference / 1000, 0);
    }

    public void checkFieldsNotNull() throws InvalidInputException {

        if (duration < 0) {
            throw new InvalidInputException("Invalid duration: ", String.valueOf(duration));
        }

        if (board == null || board.equals("")) {
            throw new InvalidInputException("Invalid board: ", "null");
        }

        if (id < 0) {
            throw new InvalidInputException("Invalid id: ", String.valueOf(id));
        }

        if (token == null || token.equals("")) {
            throw new InvalidInputException("Invalid token: ", "null");
        }

    }
}
