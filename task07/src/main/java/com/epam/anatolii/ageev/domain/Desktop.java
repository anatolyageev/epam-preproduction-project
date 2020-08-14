package com.epam.anatolii.ageev.domain;

import java.math.BigDecimal;

public interface Desktop {
    Long getId();

    void setId(Long id);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    String getFormFactor();

    void setFormFactor(String formFactor);

    String getProcessorType();

    void setProcessorType(String processorType);

    Double getProcessorFrequency();

    void setProcessorFrequency(Double processorFrequency);

    Integer getInstalledMemory();

    void setInstalledMemory(Integer installedMemory);
}
