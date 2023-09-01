package com.company.gamestore.controller;


import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TshirtController.class)
@AutoConfigureMockMvc
public class TshirtControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TshirtRepository tshirtRepository;

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    public void setUp() {
        tshirtRepository.deleteAll();
    }


    @Test
    public void shouldCreateTshirtOnPost() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("A graphic tshirt with a whale.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);

        mockMvc.perform(post("/tshirts")
                        .content(mapper.writeValueAsString(tshirt))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAllTshirtsOnGet() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("A graphic tshirt with a whale.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);
        tshirtRepository.save(tshirt);

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setSize("medium");
        tshirt1.setColor("red");
        tshirt1.setDescription("A button up red shirt.");
        tshirt1.setPrice(new BigDecimal("39.99"));
        tshirt1.setQuantity(2);
        tshirtRepository.save(tshirt1);


        mockMvc.perform(get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTshirtOnPut() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("A graphic tshirt with a whale.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);

        tshirtRepository.save(tshirt);

        tshirt.setSize("medium");

        mockMvc.perform(put("/tshirts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(tshirt)))
                        .andDo(print())
                        .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnTshirtById() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("A graphic tshirt with a whale.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);

        String outputJson = mapper.writeValueAsString(tshirt);

        mockMvc.perform(get("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void shouldDeleteTshirtOnDelete() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("small");
        tshirt.setColor("blue");
        tshirt.setDescription("A graphic tshirt with a whale.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);
        tshirtRepository.save(tshirt);

        mockMvc.perform(delete("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetTshirtsByColorOnGet() throws Exception {
        mockMvc.perform(get("/tshirts/color/red"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTshirtsBySizeOnGet() throws Exception {
        mockMvc.perform(get("/tshirts/size/medium"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
