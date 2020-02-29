package com.example.restservice;

import com.example.restservice.Exceptions.InvalidInputException;
import com.example.restservice.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class BoardFactory {

    private static String boardPath;

    @PostConstruct
    private void init() {
        StringBuilder sb = new StringBuilder();
        sb.append(Common.ENV_PATH);
        sb.append(Common.FILE_DEFAULT_BOARD);
        boardPath = sb.toString();
    }

    public static Board generateDefaultBoard() {

        File file = new File(boardPath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String input = br.readLine();
            br.close();
            return new Board(input);
        } catch (IOException e) {
            throw new ResourceNotFoundException("File not found: " + boardPath);
        }

    }

    public static Board generateRandomBoard() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Board.TILES_COUNT; i++) {

            sb.append(RandomGenerator.nextTile());

            if (!(i == Board.TILES_COUNT - 1)) {
                sb.append(", ");
            }
        }

        return new Board(sb.toString().equals("")? Common.DEFAULT_BOARD : sb.toString());
    }

    public static Board generateBoard(String s) throws InvalidInputException {
        String[] arr = s.split(", ");
        if (arr.length != 16) {
            throw new InvalidInputException("board length: ", String.valueOf(arr.length));
        }
        for (String tile : arr) {
            if (!(Character.isAlphabetic(tile.charAt(0)) || tile.equals("*"))) {
                throw new InvalidInputException("tile: ", tile);
            }
        }

        return new Board(s);
    }

}
