package com.company.gamestore.controller;


import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import com.company.gamestore.servicelayer.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TshirtController {
//    @Autowired
//    TshirtRepository tshirtRepository;
    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTshirt(@RequestBody Tshirt tshirt) {
//        tshirtRepository.save(tshirt);
        serviceLayer.saveTshirt(tshirt);
    }

    @GetMapping("/tshirts")
    public List<Tshirt> getAllTshirts() {
//        return tshirtRepository.findAll();
        return serviceLayer.findAllTshirts();
    }

    @PutMapping("/tshirts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody Tshirt tshirt) {
//        tshirtRepository.save(tshirt);
        serviceLayer.saveTshirt(tshirt);
    }

    @DeleteMapping("/tshirts/{tshirtId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirt(@PathVariable int tshirtId) {
//        tshirtRepository.deleteById(tshirtId);
        serviceLayer.deleteTshirtById(tshirtId);
    }

//    @GetMapping("/tshirts/{tshirtId}")
//    @ResponseStatus(HttpStatus.OK)
//    public Tshirt getTshirtById(@PathVariable int tshirtId) {
//        return serviceLayer.findTshirtById(tshirtId);
//    }

    @GetMapping("/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@PathVariable String color) {
//        return tshirtRepository.findTshirtsByColor(color);
        return serviceLayer.findAllTshirtsByColor(color);
    }

    @GetMapping("/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@PathVariable String size) {
//        return tshirtRepository.findTshirtsBySize(size);
        return serviceLayer.findAllTshirtsBySize(size);
    }
}
