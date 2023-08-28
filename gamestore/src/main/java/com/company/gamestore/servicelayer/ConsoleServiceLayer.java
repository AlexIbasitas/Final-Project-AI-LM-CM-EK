package com.company.gamestore.servicelayer;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConsoleServiceLayer {
    private ConsoleRepository consoleRepo;
    @Autowired
    public ConsoleServiceLayer(ConsoleRepository consoleRepo){
        this.consoleRepo = consoleRepo;
    }

    public Console saveConsole(Console console){
        return consoleRepo.save(console);
    }

    public List<Console> findAllConsoles(){
        return consoleRepo.findAll();
    }

    public Console findById(Integer consoleID){
        Optional<Console> console = consoleRepo.findById(consoleID);
        if (console.isPresent()) {
            return console.get();
        } else {
            return null;
        }
    }

    public void deleteConsole(Integer consoleID){
        consoleRepo.deleteById(consoleID);
    }

    public void findByManufacturer(String manufacturer){
        consoleRepo.findByManufacturer(manufacturer);
    }
}
