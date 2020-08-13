package com.epam.anatolii.ageev.proxy.unmodifiable_proxy;


import com.epam.anatolii.ageev.domain.Desktop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DesktopInvocationHendler implements InvocationHandler {
    Desktop desktop;

    public DesktopInvocationHendler(Desktop desktop) {
        this.desktop = desktop;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().startsWith("set")){
            throw new UnsupportedOperationException("set method could not be invoke");
        }
        return method.invoke(desktop,args);
    }
}
