package com.epam.anatolii.ageev.eshop.admin_services.strategy;

import com.epam.anatolii.ageev.eshop.constants.BuildersConstants;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.LinkedHashMap;
import java.util.Map;

public class ItemBuildContainer {
    private Map<Integer, BuildItem> builders = new LinkedHashMap<>();

    public ItemBuildContainer(Strategy strategy) {
        builders.put(BuildersConstants.BUILD_DESCTOP, new BuildDesctop(strategy));
        builders.put(BuildersConstants.BUILD_LAPTOP, new BuildLaptop(strategy));
        builders.put(BuildersConstants.BUILD_SERVER, new BuildServer(strategy));
    }

    public BuildItem getBuilder(Integer builderID) {
        if (builderID == null || !builders.containsKey(builderID)) {
            System.out.println("No such command exist. Please try one more time");
            return null;
        }
        return builders.get(builderID);
    }

    public Map<Integer, BuildItem> getBuilders() {
        LinkedHashMap<Integer, BuildItem> copyBuilders = new LinkedHashMap<>(builders);
        return copyBuilders;
    }
}
