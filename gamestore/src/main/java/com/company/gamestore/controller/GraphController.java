package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    @Autowired
    GameRepository gamerepo;

    @Autowired
    ConsoleRepository consolerepo;


    @QueryMapping
    public Game gameById(@Argument int id){
        Optional<Game> game = gamerepo.findById(id);
        if (game.isPresent()){
            return game.get();
        } else {
            return null;
        }
    }

    @QueryMapping
    public List<Game> games() {
        List<Game> game = gamerepo.findAll();
        return game;
    }
    @QueryMapping
    public List<Game> gameByTitle(@Argument String title) {
        Optional<List<Game>> game = gamerepo.findGamesByTitle(title);
        if(game.isPresent()){
            return game.get();
        }
        return null;
    }

    @QueryMapping
    public List<Game> gameByStudio(@Argument String studio) {
        Optional<List<Game>> game = gamerepo.findGamesByStudio(studio);
        if(game.isPresent()){
            return game.get();
        }
        return null;
    }

    @QueryMapping
    public List<Game> gameByESRB(@Argument String esrbRating) {
        Optional<List<Game>> game = gamerepo.findGamesByStudio(esrbRating);
        if(game.isPresent()){
            return game.get();
        }
        return null;
    }

    @QueryMapping
    public List<Console> consoles(){
        return consolerepo.findAll();
    }

    @QueryMapping
    public Console consolebyID(@Argument Integer id){
        Optional<Console> console = consolerepo.findById(id);
        return console.isPresent() ? console.get() : null;
    }

    @QueryMapping
    public List<Console> consolebyManufacturer(@Argument String manufacturer){
        List<Console> console = consolerepo.findByManufacturer(manufacturer);
        return console;
    }
}