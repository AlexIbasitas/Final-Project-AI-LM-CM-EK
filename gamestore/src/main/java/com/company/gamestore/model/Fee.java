package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "fee")
public class Fee implements Serializable {

    @Id
    @Column(name = "product_type")
    private String product_type;

    @NotNull
    private BigDecimal fee;

    public Fee() {
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fee fee1 = (Fee) o;
        return Objects.equals(product_type, fee1.product_type) && Objects.equals(fee, fee1.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_type, fee);
    }

    @Override
    public String toString() {
        return "Fee{" +
                "product_type='" + product_type + '\'' +
                ", fee=" + fee +
                '}';
    }
}
