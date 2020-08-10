package com.epam.anatolii.ageev.eshop.admin_services.strategy;

import com.epam.anatolii.ageev.eshop.admin_services.strategy.strategy_impl.AutoStrategyImpl;
import com.epam.anatolii.ageev.eshop.admin_services.strategy.strategy_impl.ManualStrategyImpl;

public enum Mode {
    MANUAL {
        @Override
        public Strategy getImplementation() {
            return new ManualStrategyImpl();
        }
    }, AUTOMATIC{
        @Override
        public Strategy getImplementation() {
            return new AutoStrategyImpl();
        }
    };

    public Strategy getImplementation() {
        return null;
    }
}
