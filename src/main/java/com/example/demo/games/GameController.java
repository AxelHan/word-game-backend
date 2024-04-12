package com.example.demo.games;

import com.example.demo.helpfunctions.Responses;
import com.example.demo.helpfunctions.ApiHelpers;
import com.example.demo.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<GameListResponse> getAllGames(){
        GameListResponse gameListResponse = new GameListResponse();
        List<Game> games = this.gameRepository.findAll();
        gameListResponse.set(games);
        return new ResponseEntity<>(gameListResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createGame(@RequestBody Game game) {
        if (ApiHelpers.isInvalidGame(game)){
            return Responses.badRequest("create", game.getClass().getSimpleName());
        }
        Game createdGame = this.gameRepository.save(game);
        GameResponse gameResponse = new GameResponse();
        gameResponse.set(createdGame);
        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
   }

    @DeleteMapping
    public ResponseEntity<String>deleteAllGames(){
        try {
            // Delete all games from the database
            gameRepository.deleteAll();
            // Return a success response
            return ResponseEntity.ok().body("All games deleted successfully.");
        } catch (Exception e) {
            // Return an error response if an exception occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete games: " + e.getMessage());
        }
    }
}
