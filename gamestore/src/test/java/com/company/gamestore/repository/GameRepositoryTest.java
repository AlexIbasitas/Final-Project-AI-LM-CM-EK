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
    GameRepository gameRepository;

    private BigDecimal decimal;


    private Game game2;

    private final Set<Game> games = new HashSet<>();


    @BeforeEach
    public void setUp() throws Exception {
        gameRepository.deleteAll();
        decimal = BigDecimal.valueOf(2.35);
    }

    @Test
    public void addGame() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gameRepository.save(game);

        // Assert
        Optional game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game);
    }

    @Test
    public void getGameById() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gameRepository.save(game);

        // Assert
        Optional game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game);
    }

    @Test
    public void getGameByStudio() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gameRepository.save(game);

        // Assert
        Optional<List<Game>> game1 = gameRepository.findGamesByStudio(game.getStudio());
        assertEquals(1, game1.get().size());
    }

    @Test
    public void getGameByTitle() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gameRepository.save(game);

        // Assert
        Optional<List<Game>> game1 = gameRepository.findGamesByTitle(game.getTitle());
        assertEquals(1, game1.get().size());
    }

    @Test
    public void getGameByESRB() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gameRepository.save(game);

        // Assert
        Optional<List<Game>> game1 = gameRepository.findGamesByEsrbRating(game.getEsrbRating());
        assertEquals(1, game1.get().size());
    }

    @Test
    public void getAllGames() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gameRepository.save(game);

        Game game2 = new Game();
        game2.setEsrbRating("Mature");
        game2.setTitle("Until Dawn");
        game2.setDescription("choices r deadly");
        game2.setPrice(decimal);
        game2.setStudio("Supermassive Games");
        game2.setQuantity(1);

        gameRepository.save(game2);

        List<Game> games = gameRepository.findAll();
        assertEquals(games.size(), 2);
    }

    @Test
    public void updateGame() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        game.setEsrbRating("Everyone");
        gameRepository.save(game);

        // Assert
        Optional game1 = gameRepository.findById(game.getId());
        assertEquals(game1.get(), game);


    }

    @Test
    public void deleteGame() {
        // Arrange
        Game game = new Game();
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        gameRepository.save(game);

        // Assert
        gameRepository.deleteById(game.getId());
        Optional game1 = gameRepository.findById(game.getId());
        assertFalse(game1.isPresent());
    }
}
