package com.company.gamestore.controller;


import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.servicelayer.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TshirtController {
    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/")
    public String test() {
        return "Test Complete";
    }

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTshirt(@RequestBody @Valid Tshirt tshirt) {
        serviceLayer.saveTshirt(tshirt);
    }

    @GetMapping("/tshirts")
    public List<Tshirt> getAllTshirts() {
        return serviceLayer.findAllTshirts();
    }

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid Tshirt tshirt) {
        serviceLayer.saveTshirt(tshirt);
    }

    @DeleteMapping("/tshirts/{tshirtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int tshirtId) {
        serviceLayer.deleteTshirtById(tshirtId);
    }

    @GetMapping("/tshirts/{tshirtId}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int tshirtId) {
        return serviceLayer.findTshirtById(tshirtId);
    }

    @GetMapping("/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@PathVariable String color) {
        return serviceLayer.findAllTshirtsByColor(color);
    }

    @GetMapping("/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@PathVariable String size) {
        return serviceLayer.findAllTshirtsBySize(size);
    }
}
