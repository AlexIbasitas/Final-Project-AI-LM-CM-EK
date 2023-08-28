package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ConsoleRepository consoleRepo;

    // ObjectMapper used to convert Java objects to JSON and vice versa
    private ObjectMapper mapper = new ObjectMapper();
    private List<Console> consoleList = new ArrayList<>();

    @Test
    public void shouldReturnConsolesByManufacturer() throws Exception {
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);
        consoleRepo.save(test);
        String outputJson = mapper.writeValueAsString(test);

        // ACT
        mockMvc.perform(get("/consoles/manufacturer/Microsoft"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllConsoles() throws Exception {
        // ARRANGE
        // Convert Java object to JSON
        String outputJson = mapper.writeValueAsString(consoleList);

        // ACT
        mockMvc.perform(get("/consoles/all"))                // Perform the GET request
                .andDo(print())                          // Print results to console
                .andExpect(status().isOk());              // ASSERT (status code is 200)
    }


    @Test
    public void shouldReturnNewCustomerOnPostRequest() throws Exception {
        // ARRANGE
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);

        // Convert Java Object to JSON
        String inputJson = mapper.writeValueAsString(test);

        // ACT
        mockMvc.perform(
                        post("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnCustomerById() throws Exception {

        Console test = new Console();
        test.setId(1);
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);
        consoleRepo.save(test);
        String outputJson = mapper.writeValueAsString(test);

        mockMvc.perform(get("/consoles/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdate() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates No Content
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(3);
        test.setId(2);
        String inputJson = mapper.writeValueAsString(test);

        mockMvc.perform(
                        put("/consoles")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteById() throws Exception {
        // This method returns nothing, so we're just checking for correct status code
        // In this case, code 204, which indicates no Content
        mockMvc.perform(delete("/consoles/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
