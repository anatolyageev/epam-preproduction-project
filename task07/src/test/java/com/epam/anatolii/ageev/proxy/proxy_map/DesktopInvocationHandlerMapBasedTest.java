package com.epam.anatolii.ageev.proxy.proxy_map;

import com.epam.anatolii.ageev.domain.Desktop;
import com.epam.anatolii.ageev.domain.DesktopImpl;
import com.epam.anatolii.ageev.proxy.factory.ProxyMapBasedDesktopFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Random;

import static org.junit.Assert.*;

public class DesktopInvocationHandlerMapBasedTest {
    private Desktop desktopProxy;
    private Desktop desktop;
    private Random random;

    @Before
    public void init() throws IllegalAccessException {
        desktop = new DesktopImpl(1L, new BigDecimal(333), "fx", "Intel", 333.2, 32);
        desktopProxy = new ProxyMapBasedDesktopFactory(desktop).newDesktopProxy(desktop);
        random = new Random();
    }

    @Test
    public void getFieldFromProxyObject_ShouldReturnTrue() {
        assertEquals(desktopProxy.getFormFactor(), (desktop.getFormFactor()));
    }

    @Test
    public void setFieldToProxyClass_ShouldNotBeEqual() {
        desktopProxy.setFormFactor(desktopProxy.getFormFactor() + random.nextInt(100));
        assertNotEquals(desktopProxy.getFormFactor(), desktop.getFormFactor());
    }
}