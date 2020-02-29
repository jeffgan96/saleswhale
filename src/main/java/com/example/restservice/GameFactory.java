package com.example.restservice;

import com.example.restservice.Exceptions.InvalidInputException;
import org.springframework.stereotype.Component;

/**
 * A factory to create games
 */
@Component
public class GameFactory {


    public Game createGame(Integer duration, boolean isRandom, String board, Integer id, String token) throws InvalidInputException {
        Game game = new Game();

        if (duration <= 0) {
            throw new InvalidInputException("duration:", String.valueOf(duration));
        }

        if (isRandom) {
            game.setBoard(BoardFactory.generateRandomBoard());
        } else {
            if (board == null || board.equals("")) {
                game.setBoard(BoardFactory.generateDefaultBoard());
            } else {
                game.setBoard(BoardFactory.generateBoard(board));
            }
        }

        game.setStartTime(System.currentTimeMillis());
        game.setId(id);
        game.setRandom(isRandom);
        game.setToken(token);
        game.setTimeLeft(duration);
        game.setDuration(duration);

        game.checkFieldsNotNull();
        return game;
    }


}
