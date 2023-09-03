package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    GameRepository gamerepo;

    @GetMapping("/games")
    public List<Game> getGames() {
        return gamerepo.findAll();
    }

    @GetMapping("/games/{id}")
    public Game getGameById(@PathVariable int id) {
        Optional<Game> returnVal = gamerepo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @GetMapping("/games/studio/{studio}")
    public List<Game> getGameByStudio(@PathVariable String studio) {
        Optional<List<Game>> returnVal = gamerepo.findGamesByStudio(studio);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        return null;
    }

    @GetMapping("/games/title/{title}")
    public List<Game> getGameByTitle(@PathVariable String title) {
        Optional<List<Game>> returnVal = gamerepo.findGamesByTitle(title);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        return null;
    }

    @GetMapping("/games/ESRB/{esrbRating}")
    public List<Game> getGameByESRB(@PathVariable String esrbRating) {
        Optional<List<Game>> returnVal = gamerepo.findGamesByEsrbRating(esrbRating);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        return null;
    }

    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody @Valid Game game) {
        return gamerepo.save(game);
    }

    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody @Valid Game game) {
        gamerepo.save(game);
    }

    @DeleteMapping("/games/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        gamerepo.deleteById(id);
    }
}