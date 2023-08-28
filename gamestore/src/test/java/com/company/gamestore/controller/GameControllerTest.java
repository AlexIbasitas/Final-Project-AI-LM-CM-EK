package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameController.class)
public class GameControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    private List<Game> gameList;

    private BigDecimal decimal;

    @MockBean
    private GameRepository gamerepo;

    @Test
    public void shouldReturnGame() throws Exception {
        Game game = new Game();
        decimal = BigDecimal.valueOf(2.35);
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);

        String inputJson = mapper.writeValueAsString(game);

        Game game2 = new Game();
        game2.setEsrbRating("Mature");
        game2.setTitle("Until Dawn");
        game2.setDescription("choices r deadly");
        game2.setPrice(decimal);
        game2.setStudio("Supermassive Games");
        game2.setQuantity(1);

        String outputJson = mapper.writeValueAsString(game2);

        mockMvc.perform(post("/games")
                        .content(inputJson).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());

    }

    @Test
    public void shouldReturnGameById() throws Exception {
        mockMvc.perform(get("/games/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGameByStudio() throws Exception {
        Game game = new Game();
        decimal = BigDecimal.valueOf(2.35);
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);
        game.setId(1);

        mockMvc.perform(get("/games/studio/Square Enix"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGameByTitle() throws Exception {
        Game game = new Game();
        decimal = BigDecimal.valueOf(2.35);
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);
        game.setId(1);

        mockMvc.perform(get("/games/title/Life is Strange"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGameByESRB() throws Exception {
        Game game = new Game();
        decimal = BigDecimal.valueOf(2.35);
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square Enix");
        game.setQuantity(1);
        game.setId(1);

        mockMvc.perform(get("/games/ESRB/Teen"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldReturnAllGames() throws Exception {

        mockMvc.perform(get("/games")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateGame() throws Exception {
        Game game = new Game();
        decimal = BigDecimal.valueOf(2.35);
        game.setEsrbRating("Teen");
        game.setTitle("Life is Strange");
        game.setDescription("choices matter");
        game.setPrice(decimal);
        game.setStudio("Square");
        game.setQuantity(1);
        game.setId(1);

        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(
                        put("/games")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteGameById() throws Exception {
        mockMvc.perform(delete("/games/1")).andDo(print()).andExpect(status().isNoContent());

    }

}
