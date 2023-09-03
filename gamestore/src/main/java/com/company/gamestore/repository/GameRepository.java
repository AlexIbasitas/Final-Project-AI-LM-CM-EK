package com.company.gamestore.repository;

import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<List<Game>> findGamesByStudio(String studio);
    Optional<List<Game>> findGamesByEsrbRating(String esrbRating);
    Optional<List<Game>> findGamesByTitle(String title);
}
