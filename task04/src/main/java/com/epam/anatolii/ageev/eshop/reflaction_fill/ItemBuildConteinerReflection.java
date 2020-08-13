package com.epam.anatolii.ageev.eshop.reflaction_fill;

import com.epam.anatolii.ageev.eshop.constants.BuildersConstants;
import com.epam.anatolii.ageev.eshop.domain.Desktop;
import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.domain.Laptop;
import com.epam.anatolii.ageev.eshop.domain.Server;

import java.util.LinkedHashMap;
import java.util.Map;

public class ItemBuildConteinerReflection {
    private Map<Integer, Item> builders = new LinkedHashMap<>();

    public ItemBuildConteinerReflection() {
        builders.put(BuildersConstants.BUILD_DESCTOP, new Desktop());
        builders.put(BuildersConstants.BUILD_LAPTOP, new Laptop());
        builders.put(BuildersConstants.BUILD_SERVER, new Server());
    }

    public Item getItem(Integer itemId) {
        if (itemId == null || !builders.containsKey(itemId)) {
            System.out.println("No such command exist. Please try one more time");
            return null;
        }
        return builders.get(itemId);
    }

    public Map<Integer, Item> getBuilders() {
        return new LinkedHashMap<>(builders);
    }
}
