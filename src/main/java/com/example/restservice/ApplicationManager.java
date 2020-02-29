package com.example.restservice;

import com.example.restservice.Exceptions.InvalidInputException;
import com.example.restservice.Exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the state of the application which includes all existing games
 */

@Component
public class ApplicationManager {

    private GameFactory factory;
    private Map<Integer, Game> map = new HashMap<>(); //stores all the games

    public ApplicationManager(GameFactory factory) {
        this.factory = factory;
    }

    public Map<String, Object> printGameAsJson(int id) throws ResourceNotFoundException {
        if (!map.containsKey(id)) {
            throw new ResourceNotFoundException("Game id " + id);
        }
        Map<String, Object> result = new HashMap<>();
        Game game = map.get(id);

        result.put("id", game.getId());
        result.put("token", game.getToken());
        result.put("duration", game.getDuration());
        result.put("board", game.printBoard());
        result.put("time_left", game.getTimeLeft());
        result.put("points", game.getPoints());

        return result;

    }

    public int startGame(Integer duration, Boolean isRandom, String board) throws InvalidInputException {
        if (duration == null || isRandom == null) {
            throw new InvalidInputException("duration/random value: ", "null");
        }

        int id = map.size() + 1;
        String token = RandomGenerator.generateUserToken();
        Game logic = factory.createGame(duration, isRandom, board, id, token);
        map.put(id, logic);
        return id;
    }

    /**
     *  Returns true if the move is made successfully, returns false otherwise
     */
    public boolean makeMove(int id, String token, String word) throws InvalidInputException {
        if (!checkTokenValidity(id, token)) {
            throw new InvalidInputException("token: ", token);
        }

        if (!map.containsKey(id)) {
            throw new InvalidInputException("id: ", String.valueOf(id));
        }

        return map.get(id).makeMove(word);
    }

    public boolean checkTokenValidity(int id, String token) {
        if (!map.containsKey(id)) {
            return false;
        }
        return map.get(id).checkTokenValidity(token);
    }


}
