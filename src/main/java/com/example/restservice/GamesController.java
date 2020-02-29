package com.example.restservice;

import java.util.Map;

import com.example.restservice.Exceptions.InvalidInputException;
import com.example.restservice.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GamesController {

	private ApplicationManager manager;

	public GamesController(ApplicationManager manager) {
		this.manager = manager;
	}

	@PostMapping("/games")
	public ResponseEntity<Object> startGame(@RequestBody GameInfo info) {

		try {
			int id = this.manager.startGame(info.getDuration(), info.isRandom(), info.getBoard());
			Map<String, Object> map = manager.printGameAsJson(id);
			//TODO remove hardcoding
			map.remove("points");
			map.remove("time_left");
			return new ResponseEntity<>(map, HttpStatus.CREATED);
		} catch (InvalidInputException e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "/games/{id}")
	public ResponseEntity<Object> getBoard(@PathVariable int id) {
		try {
			Map<String, Object> result = manager.printGameAsJson(id);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(path = "/games/{id}")
	public ResponseEntity<Object> playGame(@RequestBody GameInfo info, @PathVariable int id) {
		try {
			boolean result = manager.makeMove(id, info.getToken(), info.getWord());
			if (!result) {
				return new ResponseEntity<>(Common.DEFAULT_INVALID_ERROR_MESSAGE, HttpStatus.ACCEPTED);
			}
		} catch (InvalidInputException e) {
			//if either game not found or token not correct, then return this
			return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(manager.printGameAsJson(id), HttpStatus.OK);
	}

}
