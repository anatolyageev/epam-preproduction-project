package com.epam.anatolii.ageev.eshop.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Server extends Desktop{
    /**
     * Number of CPU
     */
    private Integer cpuNumber;

    /**
     * Is IPMI is present in the erver
     */
    private Boolean isIpmi;


    /**
     *  Constructor for create an empty Server object.
     */
    public Server() {}


    /**
     * Creates new Server with the next values
     *
     * @param id
     * @param price
     * @param processorType
     * @param processorFrequency
     * @param installedMemory
     * @param formFactor
     * @param cpuNumber
     * @param isIpmi
     */
    public Server(Long id, BigDecimal price, String processorType, Double processorFrequency, Integer installedMemory, String formFactor, Integer cpuNumber, Boolean isIpmi) {
        super(id, price, processorType, processorFrequency, installedMemory, formFactor);
        this.cpuNumber = cpuNumber;
        this.isIpmi = isIpmi;
    }

    public Integer getCpuNumber() {
        return cpuNumber;
    }

    public void setCpuNumber(Integer cpuNumber) {
        this.cpuNumber = cpuNumber;
    }

    public Boolean getIpmi() {
        return isIpmi;
    }

    public void setIpmi(Boolean ipmi) {
        isIpmi = ipmi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Server server = (Server) o;
        return Objects.equals(cpuNumber, server.cpuNumber) &&
                Objects.equals(isIpmi, server.isIpmi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpuNumber, isIpmi);
    }

    @Override
    public String toString() {
        return "Server{" + super.toString() +
                "cpuNumber=" + cpuNumber +
                ", isIpmi=" + isIpmi +
                '}';
    }
}
