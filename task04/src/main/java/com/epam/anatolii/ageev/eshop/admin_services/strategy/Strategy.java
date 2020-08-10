package com.epam.anatolii.ageev.eshop.admin_services.strategy;

import java.math.BigDecimal;

public interface Strategy {
    Long initId();

    BigDecimal initPrice();

    String initProcessorType();

    Double initProcessorFrequency();

    Integer initInstalledMemory();

    String initFormFactor();

    Double initScreenSize();

    Integer initCpuNumber();

    Boolean initIsIpmi();
}
