package com.company.gamestore.repository;
import com.company.gamestore.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gamerepo;

    private BigDecimal decimal;

    private Game game;
    private Game game2;

    private final Set<Game> games = new HashSet<>();


    @BeforeEach
    public void setUp() throws Exception {
        gamerepo.deleteAll();
        decimal = BigDecimal.valueOf(2.35);

        game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gamerepo.save(game);

        game2 = new Game();
        game2.setEsrbRating("Mature");
        game2.setTitle("Until Dawn");
        game2.setDescription("choices r deadly");
        game2.setPrice(decimal);
        game2.setStudio("Supermassive Games");
        game2.setQuantity(1);

        gamerepo.save(game2);
    }

    @Test
    public void addGame() {
        Optional game1 = gamerepo.findById(game.getId());
        assertEquals(game1.get(), game);
    }

    @Test
    public void getGameById() {
        Optional game1 = gamerepo.findById(game.getId());
        assertEquals(game1.get(), game);
    }

    @Test
    public void getGameByStudio() {
        List<Game> game1 = gamerepo.findGamesByStudio(game.getStudio());
        assertEquals(1, game1.size());
    }

    @Test
    public void getGameByTitle() {
        List<Game> game1 = gamerepo.findGamesByTitle(game.getTitle());
        assertEquals(1, game1.size());
    }

    @Test
    public void getGameByESRB() {
        List<Game> game1 = gamerepo.findGamesByEsrbRating(game.getEsrbRating());
        assertEquals(1, game1.size());
    }

    @Test
    public void getAllGames() {
        List<Game> games = gamerepo.findAll();
        assertEquals(games.size(), 2);
    }

    @Test
    public void updateGame() {
        game.setEsrbRating("Everyone");
        gamerepo.save(game);

        Optional game1 = gamerepo.findById(game.getId());
        assertEquals(game1.get(), game);


    }

    @Test
    public void deleteGame() {
        gamerepo.deleteById(game.getId());
        Optional game1 = gamerepo.findById(game.getId());
        assertFalse(game1.isPresent());
    }
}
