package com.epam.anatolii.ageev.eshop.reflaction_fill;

import com.epam.anatolii.ageev.eshop.admin_services.strategy.Mode;
import com.epam.anatolii.ageev.eshop.annotations.FieldDescription;
import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.utils.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.ResourceBundle;

public class BuildItemReflaction <T extends Item>{
    private Mode mode;
    private ResourceBundle resourceBundle;
    private State state;
    private ReflectionUtils reflactionUtils;

    public BuildItemReflaction(Mode mode, ResourceBundle resourceBundle){
        this.mode = mode;
        this.resourceBundle = resourceBundle;
        if(Mode.MANUAL.equals(mode)){
            this.state = new StateManual(this);
        }else if(Mode.AUTOMATIC.equals(mode)){
            this.state = new StateAuto(this);
        }

    }

    public State getState() {
        return state;
    }

    public Mode getMode() {
        return mode;
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public ReflectionUtils getReflactionUtils() {
        return reflactionUtils;
    }
}
