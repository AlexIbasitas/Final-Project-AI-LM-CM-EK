package com.company.gamestore.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {

    private int id;

    @NotNull(message = "You must provide a name.")
    private String name;

    @NotNull(message = "You must provide a street.")
    private String street;

    @NotNull(message = "You must provide a city.")
    private String city;

    @NotNull(message = "You must provide a state.")
    @Size(min = 2, max = 2, message = "State must be exactly two letters.")
    private String state;

    @NotNull(message = "You must provide a zip.")
    @NotEmpty(message = "Zipcode cannot be empty.")
    @Size(min = 5, max = 5, message = "Zipcode must be exactly five numbers long.")
    private String zip;

    @NotNull(message = "You must provide an item type.")
    @NotEmpty(message = "Item type cannot be empty.")
    private String item_type;

    @NotNull(message = "You must provide an item ID.")
    private int item_id;

    private BigDecimal unit_price;

    @NotNull(message = "You must provide a quantity.")
    @Min(value = 1, message = "Quantity must be at least one.")
    private int quantity;

    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processing_fee;
    private BigDecimal total;


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

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessing_fee() {
        return processing_fee;
    }

    public void setProcessing_fee(BigDecimal processing_fee) {
        this.processing_fee = processing_fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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