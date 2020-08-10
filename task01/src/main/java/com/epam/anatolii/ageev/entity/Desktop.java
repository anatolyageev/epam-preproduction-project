package com.epam.anatolii.ageev.entity;

import java.util.Objects;

public class Desktop extends Computer {
    private String formFactor;

    /**
     *  Constructor for create an empty Desktop object.
     */

    public Desktop() {

    }

    /**
     * Creates new Desktop with the next values
     *
     * @param id
     * @param price
     * @param processorType
     * @param processorFrequency
     * @param installedMemory
     * @param formFactor
     */
    public Desktop(Long id, Double price, String processorType, Double processorFrequency, Integer installedMemory, String formFactor) {
        super(id, price, processorType, processorFrequency, installedMemory);
        this.formFactor = formFactor;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Desktop desktop = (Desktop) o;
        return Objects.equals(formFactor, desktop.formFactor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), formFactor);
    }

    @Override
    public String toString() {
        return "Desktop{" + super.toString() +
                "formFactor='" + formFactor + '\'' +
                '}';
    }
}
