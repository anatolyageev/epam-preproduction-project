package com.epam.anatolii.ageev.eshop.reflaction_fill;

import com.epam.anatolii.ageev.eshop.admin_services.strategy.strategy_impl.AutoStrategyImpl;
import com.epam.anatolii.ageev.eshop.annotations.FieldDescription;
import com.epam.anatolii.ageev.eshop.domain.Item;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class StateAuto extends State {

    StateAuto(BuildItemReflaction buildItemReflaction) {
        super(buildItemReflaction);
    }

    @Override
    public void build(Item item) {
        Class clazz = item.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            FieldDescription annotation = method.getAnnotation(FieldDescription.class);
            if (Objects.nonNull(annotation)) {

                Class typeInput = method.getParameterTypes()[0];
                if(typeInput == Boolean.class){
                    try {
                        method.invoke(item, AutoStrategyImpl.class.getMethod("initIsIpmi").invoke(new AutoStrategyImpl()));
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        String methodName = "init" + method.getName().substring(3);
                        method.invoke(item, AutoStrategyImpl.class.getMethod(methodName).invoke(new AutoStrategyImpl()));
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
