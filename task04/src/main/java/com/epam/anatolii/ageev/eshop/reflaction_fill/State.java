package com.epam.anatolii.ageev.eshop.reflaction_fill;

import com.epam.anatolii.ageev.eshop.domain.Item;

/**
 * Common interface for state
 */

public abstract class State {
    BuildItemReflaction buildItemReflaction;

    State(BuildItemReflaction buildItemReflaction) {
        this.buildItemReflaction = buildItemReflaction;
    }

    public abstract void build(Item item);
}
