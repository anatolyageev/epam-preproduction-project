package com.epam.anatolii.ageev.entity;

import java.util.Objects;

public class Laptop extends Computer {
    private Double screenSize;

    /**
     *  Constructor for create an empty Laptop object.
     */
    public Laptop() {

    }

    /**
     * Creates new Laptop with the next values
     *
     * @param id
     * @param price
     * @param processorType
     * @param processorFrequency
     * @param installedMemory
     * @param screenSize
     */
    public Laptop(Long id, Double price, String processorType, Double processorFrequency, Integer installedMemory, Double screenSize) {
        super(id, price, processorType, processorFrequency, installedMemory);
        this.screenSize = screenSize;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(screenSize, laptop.screenSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), screenSize);
    }

    @Override
    public String toString() {
        return "Laptop{" + super.toString() +
                "screenSize=" + screenSize +
                '}';
    }
}
