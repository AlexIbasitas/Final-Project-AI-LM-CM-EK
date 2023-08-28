package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "game")
public class Game implements Serializable {

    @Id
    @Column(name = "game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String esrbRating;

    private BigDecimal price;

    private String studio;

    private int quantity;

    private String description;

    public Game(String title, String esrbRating, String description, BigDecimal price, String studio, int quantity) {
    }

    public Game() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getId() == game.getId() && getQuantity() == game.getQuantity() && Objects.equals(getTitle(), game.getTitle()) && Objects.equals(getEsrbRating(), game.getEsrbRating()) && Objects.equals(getPrice(), game.getPrice()) && Objects.equals(getStudio(), game.getStudio()) && Objects.equals(getDescription(), game.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getEsrbRating(), getPrice(), getStudio(), getQuantity(), getDescription());
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';

    }
}
