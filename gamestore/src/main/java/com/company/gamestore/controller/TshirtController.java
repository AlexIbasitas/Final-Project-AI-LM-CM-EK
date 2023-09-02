package com.company.gamestore.controller;


import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {
    @Autowired
    TshirtRepository tshirtRepository;

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTshirt(@RequestBody @Valid Tshirt tshirt) {
        tshirtRepository.save(tshirt);
    }

    @GetMapping("/tshirts")
    public List<Tshirt> getAllTshirts() {
        return tshirtRepository.findAll();
    }

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid Tshirt tshirt) {
        tshirtRepository.save(tshirt);
    }

    @DeleteMapping("/tshirts/{tshirtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int tshirtId) {
        tshirtRepository.deleteById(tshirtId);
    }

    @GetMapping("/tshirts/{tshirtId}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int tshirtId) {
        Optional<Tshirt> tshirt = tshirtRepository.findById(tshirtId);
        if (tshirt.isPresent()) {
            return tshirt.get();
        } else {
            return null;
        }
    }

    @GetMapping("/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@PathVariable String color) {
        return tshirtRepository.findTshirtsByColor(color);
    }

    @GetMapping("/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@PathVariable String size) {
        return tshirtRepository.findTshirtsBySize(size);
    }
}
