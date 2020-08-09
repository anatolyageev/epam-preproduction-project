package com.epam.anatolii.ageev.eshop.admin_services.strategy.strategy_impl;

import com.epam.anatolii.ageev.eshop.admin_services.strategy.Strategy;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;

import java.math.BigDecimal;
import java.util.Random;

public class AutoStrategyImpl implements Strategy {
    private Random random = new Random();
    private final int MIN_PRICE = 1000;
    private final int MAX_PRICE = 10000;
    private final int MIN_FREQ = 2;
    private final int MAX_FREQ = 4;
    private final int MIN_SCREEN_SIZE = 10;
    private final int MAX_SCREEN_SIZE = 17;

    @Override
    public Long initId() {
        return random.nextLong();
    }

    @Override
    public BigDecimal initPrice() {
        return BigDecimal.valueOf(CommandUtils.getRandomDouble(MIN_PRICE,MAX_PRICE));
    }

    @Override
    public String initProcessorType() {
        return "Intel " + random.nextInt(12) ;
    }

    @Override
    public Double initProcessorFrequency() {
        return CommandUtils.getRandomDouble(MIN_FREQ,MAX_FREQ);
    }

    @Override
    public Integer initInstalledMemory() {
        return random.nextInt(128);
    }

    @Override
    public String initFormFactor() {
        return "FX " + random.nextInt(200);
    }

    @Override
    public Double initScreenSize() {
        return CommandUtils.getRandomDouble(MIN_SCREEN_SIZE,MAX_SCREEN_SIZE);
    }

    @Override
    public Integer initCpuNumber() {
        return random.nextInt(6);
    }

    @Override
    public Boolean initIsIpmi() {
        return random.nextBoolean();
    }
}
