package com.company.gamestore.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tshirt")
public class Tshirt implements Serializable {
    @Id
    @Column(name = "tshirt_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tshirtId;
    @NotEmpty(message = "You must supply a value for size.")
    private String size;
    @NotEmpty(message = "You must supply a value for color.")
    private String color;
    @NotEmpty(message = "You must supply a value for description.")
    private String description;
    @NotNull(message = "You must supply a value for price.")
    private BigDecimal price;
    @NotNull(message = "You must supply a value for quantity.")
    private Integer quantity;

    public Tshirt() {
    }

    public Tshirt(int tshirtId, String size, String color, String description, BigDecimal price, Integer quantity) {
        this.tshirtId = tshirtId;
        this.size = size;
        this.color = color;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getTshirtId() {
        return tshirtId;
    }

    public void setTshirtId(int tshirtId) {
        this.tshirtId = tshirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt = (Tshirt) o;
        return tshirtId == tshirt.tshirtId && Objects.equals(size, tshirt.size) && Objects.equals(color, tshirt.color) && Objects.equals(description, tshirt.description) && Objects.equals(price, tshirt.price) && Objects.equals(quantity, tshirt.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tshirtId, size, color, description, price, quantity);
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "tshirtId=" + tshirtId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
