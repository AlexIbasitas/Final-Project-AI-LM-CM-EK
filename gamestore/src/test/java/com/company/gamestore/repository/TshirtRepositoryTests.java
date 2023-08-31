package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TshirtRepositoryTests {
    @Autowired
    TshirtRepository tshirtRepository;

    @BeforeEach
    public void setUp() throws Exception {
        tshirtRepository.deleteAll();
    }

    @Test
    public void shouldCreateTshirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("M");
        tshirt.setColor("Red");
        tshirt.setDescription("A plain red shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);

        tshirt = tshirtRepository.save(tshirt);
        Optional<Tshirt> r = tshirtRepository.findById(tshirt.getTshirtId());
        assertEquals(r.get(), tshirt);
    }

    @Test
    public void shouldGetAllTshirts() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("M");
        tshirt.setColor("Red");
        tshirt.setDescription("A plain red shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);
        tshirt = tshirtRepository.save(tshirt);

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setSize("S");
        tshirt1.setColor("Blue");
        tshirt1.setDescription("A graphic tshirt with a whale.");
        tshirt1.setPrice(new BigDecimal("17.99"));
        tshirt1.setQuantity(1);
        tshirt1 = tshirtRepository.save(tshirt1);

        List<Tshirt> r = tshirtRepository.findAll();
        assertEquals(r.size(), 2);
    }

    @Test
    public void shouldUpdateTshirt() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("M");
        tshirt.setColor("Red");
        tshirt.setDescription("A plain red shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);
        tshirt = tshirtRepository.save(tshirt);
        tshirt.setQuantity(2);
        tshirt = tshirtRepository.save(tshirt);


        Optional<Tshirt> test1 = tshirtRepository.findById(tshirt.getTshirtId());
        assertEquals(test1.get(), tshirt);
    }

    @Test
    public void shouldDeleteTshirtById() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("M");
        tshirt.setColor("Red");
        tshirt.setDescription("A plain red shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);
        tshirt = tshirtRepository.save(tshirt);

        Optional<Tshirt> test1 = tshirtRepository.findById(tshirt.getTshirtId());
        assertEquals(test1.get(), tshirt);
        tshirtRepository.deleteById(tshirt.getTshirtId());
        test1 = tshirtRepository.findById(tshirt.getTshirtId());
        assertFalse(test1.isPresent());
    }

    @Test
    public void shouldGetTshirtsByColor() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("M");
        tshirt.setColor("Yellow");
        tshirt.setDescription("A plain yellow shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);
        tshirt = tshirtRepository.save(tshirt);

        List<Tshirt> test1 = tshirtRepository.findTshirtsByColor("Yellow");
        assertEquals(test1.size(), 1);
    }

    @Test
    public void shouldGetTshirtsBySize() {
        Tshirt tshirt = new Tshirt();
        tshirt.setSize("XL");
        tshirt.setColor("Red");
        tshirt.setDescription("A plain red shirt.");
        tshirt.setPrice(new BigDecimal("19.99"));
        tshirt.setQuantity(1);
        tshirt = tshirtRepository.save(tshirt);

        List<Tshirt> test1 = tshirtRepository.findTshirtsBySize("XL");
        assertEquals(1, test1.size());
    }

}
