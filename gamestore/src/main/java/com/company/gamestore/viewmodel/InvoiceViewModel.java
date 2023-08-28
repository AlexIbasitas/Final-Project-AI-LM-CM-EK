package com.company.gamestore.viewmodel;

import com.sun.istack.NotNull;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {

    // put jsr303 stuff here
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    @Size(min = 5, max = 5)
    private String zip;

    @NotNull
    private String item_type;

    @NotNull
    private int item_id;

    private BigDecimal unit_price;

    @NotNull
    @Min(1)
    @Max(999)
    private int quantity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return item_id == that.item_id && quantity == that.quantity && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zip, that.zip) && Objects.equals(item_type, that.item_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, street, city, state, zip, item_type, item_id, quantity);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", item_type='" + item_type + '\'' +
                ", item_id=" + item_id +
                ", quantity=" + quantity +
                '}';
    }
}