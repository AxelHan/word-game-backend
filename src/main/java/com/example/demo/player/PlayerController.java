package com.example.demo.player;


import com.example.demo.helpfunctions.ApiHelpers;
import com.example.demo.helpfunctions.Responses;
import com.example.demo.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("players")
public class PlayerController {


    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping
    public ResponseEntity<PlayerListResponse> getAllPlayers(){
        PlayerListResponse playerListResponse = new PlayerListResponse();
        List<Player> players = this.playerRepository.findAll();
        playerListResponse.set(players);
        return new ResponseEntity<>(playerListResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getPlayerById(@PathVariable int id){
        Player player = ApiHelpers.getById(id, playerRepository);
        if(player == null){
            return Responses.notFound("player");
        }
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.set(player);
        return new ResponseEntity<>(playerResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createPlayer(@RequestBody Player player){
        if(ApiHelpers.isInvalidPlayer(player)){
            return Responses.badRequest("create", player.getClass().getSimpleName());
        }
        Player createdPlayer = this.playerRepository.save(player);
        PlayerResponse playerResponse = new PlayerResponse();
        playerResponse.set(createdPlayer);
        return new ResponseEntity<>(playerResponse, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String>deleteAllPlayers(){
        try {
            // Delete all players from the database
            playerRepository.deleteAll();
            // Return a success response
            return ResponseEntity.ok().body("All players deleted successfully.");
        } catch (Exception e) {
            // Return an error response if an exception occurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete players: " + e.getMessage());
        }
    }
}
