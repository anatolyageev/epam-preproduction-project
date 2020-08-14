package com.epam.anatolii.ageev.proxy.factory;

import com.epam.anatolii.ageev.domain.Desktop;
import com.epam.anatolii.ageev.domain.DesktopImpl;
import com.epam.anatolii.ageev.proxy.proxy_map.DesktopInvocationHandlerMapBased;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class ProxyMapBasedDesktopFactory implements ProxyFactory {
    private Map<String, Object> fieldsMap;

    public ProxyMapBasedDesktopFactory(Desktop desktop) throws IllegalAccessException {
        fieldsMap = new HashMap<>();
        Field[] fields = DesktopImpl.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldsMap.put(field.getName(), field.get(desktop));
        }
    }

    @Override
    public Desktop newDesktopProxy(Desktop desktop) {
        return (Desktop) Proxy.newProxyInstance(DesktopImpl.class.getClassLoader(), DesktopImpl.class.getInterfaces(), new DesktopInvocationHandlerMapBased(this.fieldsMap));
    }
}
