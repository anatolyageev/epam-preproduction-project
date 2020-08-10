package com.epam.anatolii.ageev.eshop.admin_services.strategy.strategy_impl;

import com.epam.anatolii.ageev.eshop.admin_services.strategy.Strategy;
import com.epam.anatolii.ageev.eshop.utils.CommandUtils;

import java.math.BigDecimal;
import java.util.Random;

public class AutoStrategyImpl implements Strategy {
    private final Random random = new Random();

    @Override
    public Long initId() {
        return random.nextLong();
    }

    @Override
    public BigDecimal initPrice() {
        final int MIN_PRICE = 1000;
        final int MAX_PRICE = 10000;
        return BigDecimal.valueOf(CommandUtils.getRandomDouble(MIN_PRICE, MAX_PRICE));
    }

    @Override
    public String initProcessorType() {
        return "Intel " + random.nextInt(12) ;
    }

    @Override
    public Double initProcessorFrequency() {
        final int MIN_FREQ = 2;
        final int MAX_FREQ = 4;
        return CommandUtils.getRandomDouble(MIN_FREQ, MAX_FREQ);
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
        final int MIN_SCREEN_SIZE = 10;
        final int MAX_SCREEN_SIZE = 17;
        return CommandUtils.getRandomDouble(MIN_SCREEN_SIZE, MAX_SCREEN_SIZE);
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
