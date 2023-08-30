package com.company.gamestore.repository;

import com.company.gamestore.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Integer> {
    Fee findByProductType(String type);
}
