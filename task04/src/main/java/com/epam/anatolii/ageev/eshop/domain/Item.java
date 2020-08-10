package com.epam.anatolii.ageev.eshop.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Item implements Serializable {
    private Long id;
    private BigDecimal price;

    public Item() {
    }

    public Item(Long id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", price=" + price + ", ";
    }
}
