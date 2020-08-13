package com.epam.anatolii.ageev.eshop.reflaction_fill;

import com.epam.anatolii.ageev.eshop.annotations.FieldDescription;
import com.epam.anatolii.ageev.eshop.domain.Item;
import com.epam.anatolii.ageev.eshop.utils.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.ResourceBundle;

public class StateManual extends State {


    StateManual(BuildItemReflaction buildItemReflaction) {
        super(buildItemReflaction);
    }

    @Override
    public void build(Item item) {
        Class clazz = item.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            FieldDescription annotation = method.getAnnotation(FieldDescription.class);
            if (Objects.nonNull(annotation)) {
                outputToConsoleFieldName(buildItemReflaction.getResourceBundle(), annotation.value());
                Class typeInput = method.getParameterTypes()[0];
                try {
                    method.invoke(item, ReflectionUtils.getSupplier(typeInput).get());
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    System.err.println(ex);
                }
            }
        }
    }

    private void outputToConsoleFieldName(ResourceBundle resourceBundle, String fieldName){
        System.out.println(resourceBundle.getString(fieldName));
    }
}
