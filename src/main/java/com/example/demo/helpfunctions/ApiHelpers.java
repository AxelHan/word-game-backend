package com.example.demo.helpfunctions;

import com.example.demo.games.Game;
import com.example.demo.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public class ApiHelpers {


    public static <T> T getById(int id, JpaRepository<T, Integer> repository) {
        return repository.findById(id).orElse(null);
    }

    public static boolean isInvalidPlayer(Player player) {
        return player.getUsername() == null || player.getImgUrl() == null;
    }

    public static boolean isInvalidGame(Game game){
        return game.getType() == null;
    }
}
