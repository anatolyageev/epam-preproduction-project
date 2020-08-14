package com.epam.anatolii.ageev.proxy.proxy_map;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class DesktopInvocationHandlerMapBased implements InvocationHandler {
    private Map<String, Object> fieldsMap;

    public DesktopInvocationHandlerMapBased(Map<String, Object> fieldsMap) {
        this.fieldsMap = fieldsMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith("get")){
            StringBuilder stringBuilder = new StringBuilder(method.getName().substring(3));
            stringBuilder.setCharAt(0,Character.toLowerCase(stringBuilder.charAt(0)));
            return fieldsMap.get(stringBuilder.toString());
        }
        if(method.getName().startsWith("set")){
            StringBuilder stringBuilder = new StringBuilder(method.getName().substring(3));
            stringBuilder.setCharAt(0,Character.toLowerCase(stringBuilder.charAt(0)));
            return fieldsMap.put(stringBuilder.toString(),args[0]);
        }
        return method.invoke(proxy,args);
    }
}
