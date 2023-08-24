package com.company.gamestore.servicelayer;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {
    private TshirtRepository tshirtRepository;

    @Autowired
    public ServiceLayer(TshirtRepository tshirtRepository) {
        this.tshirtRepository = tshirtRepository;
    }



    // SAVE FOR CREATE / UPDATE
    public Tshirt saveTshirt(Tshirt tshirt) {
        return tshirtRepository.save(tshirt);
    }

    // READ
    public List<Tshirt> findAllTshirts()
    {
        return tshirtRepository.findAll();
    }

    // DELETE
    public void deleteTshirtById(Integer tshirtId)
    {
        tshirtRepository.deleteById(tshirtId);
    }

    // GET BY ID
//    public Tshirt findTshirtById(Integer tshirtId) {
//        Optional<Tshirt> tshirt = tshirtRepository.findById(tshirtId);
//        if (tshirt.isPresent()) {
//            return tshirt.get();
//        } else {
//            return null;
//        }
//    }

    // CUSTOM
    public List<Tshirt> findAllTshirtsByColor(String color)
    {
        return tshirtRepository.findTshirtsByColor(color);
    }

    public List<Tshirt> findAllTshirtsBySize(String size)
    {
        return tshirtRepository.findTshirtsBySize(size);
    }
}
