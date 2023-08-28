package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
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
    public List<Game> getGameByTitle(@Argument String title) {
        List<Game> game = gamerepo.findGamesByTitle(title);
        return game;
    }

    @QueryMapping
    public List<Game> getGameByStudio(@Argument String studio) {
        List<Game> game = gamerepo.findGamesByStudio(studio);
        return game;
    }

    @QueryMapping
    public List<Game> getGameByESRB(@Argument String esrbRating) {
        List<Game> game = gamerepo.findGamesByStudio(esrbRating);
        return game;
    }

    //Mutation mapping to set up !

//    @MutationMapping
//    public Game addGame(@Argument String title, @Argument String esrbRating, @Argument String description, @Argument BigDecimal price,  @Argument String studio, @Argument int quantity) {
//        Game game = new Game(title, esrbRating, description, price, studio, quantity);
//        return gamerepo.save(game);
//    }

//    @MutationMapping
//    public Game addGame(@Argument String title, @Argument String esrbRating, @Argument String description, @Argument BigDecimal price, @Argument String studio, @Argument int quantity) {
//        Game game = new Game(title, esrbRating, description, price, studio, quantity);
//        return gamerepo.save(game);
//    }
}


//sql file to insert data into the database
//lots of things that should throw an error, write notes on what should throw an error
//throws (specifc error) <add for the method>

//public Address updateAddressByUserId(int id, AddressDTO newAddress) throws NotFound {
// // service method here
//}

//Create Controller Advice FIRST!!
//Put what we learned in class into the project
//Use Status directly in controller
//try catch blocks | if else with != null