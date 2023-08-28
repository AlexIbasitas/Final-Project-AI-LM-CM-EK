package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ConsoleRepositoryTests {
    @Autowired
    ConsoleRepository consoleRepo;

    @BeforeEach
    public void setUp() throws Exception {
        consoleRepo.deleteAll();
    }
    @Test
    public void shouldAddConsole(){
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);

        test = consoleRepo.save(test);
        Optional<Console> retrieve = consoleRepo.findById(test.getId());
        assertEquals(retrieve.get(), test);
    }

    @Test
    public void shouldGetAllConsoles(){
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);
        test = consoleRepo.save(test);


        Console test2 = new Console();
        test2.setModel("Play Station 4");
        test2.setManufacturer("Sony");
        test2.setMemory_amount("500 GB");
        test2.setProcessor("Intel Core");
        test2.setPrice(new BigDecimal("180.99"));
        test2.setQuantity(2);
        test2 = consoleRepo.save(test2);
        List<Console> retrieve = consoleRepo.findAll();
        assertEquals(retrieve.size(), 2);
    }
    @Test
    public void shouldGetConsoleByID(){
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);
        test = consoleRepo.save(test);


        Console test2 = new Console();
        test2.setModel("Play Station 4");
        test2.setManufacturer("Sony");
        test2.setMemory_amount("500 GB");
        test2.setProcessor("Intel Core");
        test2.setPrice(new BigDecimal("122.99"));
        test2.setQuantity(2);
        test2 = consoleRepo.save(test2);
        Optional<Console> retrieve = consoleRepo.findById(test2.getId());
        assertEquals(retrieve.get(), test2);
    }

    @Test
    public void shouldUpdateConsole() {
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);
        test = consoleRepo.save(test);

        test.setPrice(new BigDecimal("300.21"));
        test.setModel("Xbox X");
        consoleRepo.save(test);

        //Assert...
        Optional<Console> test1 = consoleRepo.findById(test.getId());
        assertEquals(test1.get(), test);
    }

    @Test
    public void ShouldFindByManufacturer(){
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);
        test = consoleRepo.save(test);


        Console test2 = new Console();
        test2.setModel("Play Station 4");
        test2.setManufacturer("Sony");
        test2.setMemory_amount("500 GB");
        test2.setProcessor("Intel Core");
        test2.setPrice(new BigDecimal("180.99"));
        test2.setQuantity(2);
        test2 = consoleRepo.save(test2);

        //Assert...
        List<Console> cList = consoleRepo.findByManufacturer("Sony");

        assertEquals(cList.size(), 1);
    }

    @Test
    public void shouldDeleteConsoleById() {
        Console test = new Console();
        test.setModel("Xbox 1");
        test.setManufacturer("Microsoft");
        test.setMemory_amount("500 GB");
        test.setProcessor("Intel Core i7");
        test.setPrice(new BigDecimal("229.99"));
        test.setQuantity(2);
        test = consoleRepo.save(test);
        //Assert...
        Optional<Console> test1 = consoleRepo.findById(test.getId());
        assertEquals(test1.get(), test);
        consoleRepo.deleteById(test.getId());
        test1 = consoleRepo.findById(test.getId());
        assertFalse(test1.isPresent());
    }
}

