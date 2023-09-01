package com.company.gamestore.controller;


import com.company.gamestore.model.Console;
import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.servicelayer.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {
    @Autowired
    TshirtRepository tshirtRepo;

    @GetMapping("/")
    public String test() {
        return "Test Complete";
    }

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTshirt(@RequestBody @Valid Tshirt tshirt) {
        tshirtRepo.save(tshirt);
    }

    @GetMapping("/tshirts")
    public List<Tshirt> getAllTshirts() {
        return tshirtRepo.findAll();
    }

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody @Valid Tshirt tshirt) {
        tshirtRepo.save(tshirt);
    }

    @DeleteMapping("/tshirts/{tshirtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int tshirtId) { tshirtRepo.deleteById(tshirtId);
    }

    @GetMapping("/tshirts/{tshirtId}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable int tshirtId) {
        Optional<Tshirt> returnVal = tshirtRepo.findById(tshirtId);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        return null;
    }

    @GetMapping("/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@PathVariable String color) {
        return tshirtRepo.findTshirtsByColor(color);
    }

    @GetMapping("/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@PathVariable String size) {
        return tshirtRepo.findTshirtsBySize(size);
    }
}
