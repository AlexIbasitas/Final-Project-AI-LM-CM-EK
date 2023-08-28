package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {
    @Autowired
    ConsoleRepository repo;

    // Create a new console record
    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console addConsole(@RequestBody Console console) {
        return repo.save(console);
    }

    // Update an existing console record
    @PutMapping("/consoles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsoles(@RequestBody Console console) {
        repo.save(console);
    }

    // Delete an existing console record
    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        repo.deleteById(id);
    }

    // Find a customer record by ID
    @GetMapping("/consoles/{id}")
    public Console getConsolebyID(@PathVariable int id) {
        Optional<Console> returnVal = repo.findById(id);
        if(returnVal.isPresent()){
            return returnVal.get();
        }
        return null;
    }

    // Find console records by manufacturer
    @GetMapping("/consoles/manufacturer/{manufacturer}")
    public List<Console> getConsolebyManufacturer(@PathVariable String manufacturer) {
        return repo.findByManufacturer(manufacturer);
    }

    // Get all consoles
    @GetMapping("/consoles/all")
    public List<Console> getAllConsoles(){
        return repo.findAll();
    }

}