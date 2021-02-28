package com.epam.anatolii.ageev.proxy.factory;

import com.epam.anatolii.ageev.domain.Desktop;
import com.epam.anatolii.ageev.domain.DesktopImpl;
import com.epam.anatolii.ageev.proxy.unmodifiable_proxy.DesktopInvocationHendler;

import java.lang.reflect.Proxy;

public class ProxyUnmodifiableDesktopFactory implements ProxyFactory {

    @Override
    public Desktop newDesktopProxy(Desktop desktop) {
        return (Desktop)Proxy.newProxyInstance(DesktopImpl.class.getClassLoader(),DesktopImpl.class.getInterfaces(),new DesktopInvocationHendler(desktop));
    }
}
