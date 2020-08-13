package com.epam.anatolii.ageev.domain;


import java.math.BigDecimal;

public class DesktopImpl implements Desktop{
    private Long id;
    private BigDecimal price;
    private String formFactor;
    private String processorType;
    private Double processorFrequency;
    private Integer installedMemory;

    public DesktopImpl() {
    }

    public DesktopImpl(Long id, BigDecimal price, String formFactor, String processorType, Double processorFrequency, Integer installedMemory) {
        this.id = id;
        this.price = price;
        this.formFactor = formFactor;
        this.processorType = processorType;
        this.processorFrequency = processorFrequency;
        this.installedMemory = installedMemory;
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

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public Double getProcessorFrequency() {
        return processorFrequency;
    }

    public void setProcessorFrequency(Double processorFrequency) {
        this.processorFrequency = processorFrequency;
    }

    public Integer getInstalledMemory() {
        return installedMemory;
    }

    public void setInstalledMemory(Integer installedMemory) {
        this.installedMemory = installedMemory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DesktopImpl desktop = (DesktopImpl) o;

        if (id != null ? !id.equals(desktop.id) : desktop.id != null) return false;
        if (price != null ? !price.equals(desktop.price) : desktop.price != null) return false;
        if (formFactor != null ? !formFactor.equals(desktop.formFactor) : desktop.formFactor != null) return false;
        if (processorType != null ? !processorType.equals(desktop.processorType) : desktop.processorType != null)
            return false;
        if (processorFrequency != null ? !processorFrequency.equals(desktop.processorFrequency) : desktop.processorFrequency != null)
            return false;
        return installedMemory != null ? installedMemory.equals(desktop.installedMemory) : desktop.installedMemory == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (formFactor != null ? formFactor.hashCode() : 0);
        result = 31 * result + (processorType != null ? processorType.hashCode() : 0);
        result = 31 * result + (processorFrequency != null ? processorFrequency.hashCode() : 0);
        result = 31 * result + (installedMemory != null ? installedMemory.hashCode() : 0);
        return result;
    }
}

