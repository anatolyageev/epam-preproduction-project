package com.epam.anatolii.ageev.eshop.domain;

import com.epam.anatolii.ageev.eshop.annotations.FieldDescription;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Computer extends Item {
    private String processorType;
    private Double processorFrequency;
    private Integer installedMemory;

    public Computer() {
    }

    public Computer(Long id, BigDecimal price, String processorType, Double processorFrequency, Integer installedMemory) {
        super(id, price);
        this.processorType = processorType;
        this.processorFrequency = processorFrequency;
        this.installedMemory = installedMemory;
    }

    public String getProcessorType() {
        return processorType;
    }

    @FieldDescription("PROCESSOR_TYPE")
    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public Double getProcessorFrequency() {
        return processorFrequency;
    }

    @FieldDescription("PROCESSOR_FREQUENCY")
    public void setProcessorFrequency(Double processorFrequency) {
        this.processorFrequency = processorFrequency;
    }

    public Integer getInstalledMemory() {
        return installedMemory;
    }

    @FieldDescription("INSTALLED_MEMORY")
    public void setInstalledMemory(Integer installedMemory) {
        this.installedMemory = installedMemory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Computer computer = (Computer) o;
        return Objects.equals(processorType, computer.processorType) &&
                Objects.equals(processorFrequency, computer.processorFrequency) &&
                Objects.equals(installedMemory, computer.installedMemory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), processorType, processorFrequency, installedMemory);
    }

    @Override
    public String toString() {
        return  super.toString() +
                "processorType='" + processorType + '\'' +
                ", processorFrequency=" + processorFrequency +
                ", installedMemory=" + installedMemory +
                ", ";
    }
}
