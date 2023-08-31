package com.company.gamestore.repository;

import com.company.gamestore.model.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TShirtRepository extends JpaRepository<TShirt, Integer> {
}
