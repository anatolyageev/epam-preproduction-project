package com.epam.anatolii.ageev.entity;

import java.util.Objects;

public abstract class Item {
    private Long id;
    private Double price;

    public Item() {
    }

    public Item(Long id, Double price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
                ", price=" + price;
    }
}
