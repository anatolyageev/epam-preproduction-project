package com.epam.anatolii.ageev.proxy.unmodifiable_proxy;

import com.epam.anatolii.ageev.domain.Desktop;
import com.epam.anatolii.ageev.domain.DesktopImpl;
import com.epam.anatolii.ageev.proxy.factory.ProxyUnmodifiableDesktopFactory;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import static org.junit.Assert.*;

public class DesktopInvocationHendlerTest {

    private Desktop desktopProxy;

    @Before
    public void init() {
        Desktop desktop = new DesktopImpl(1L, new BigDecimal(333), "fx", "Intel", 333.2, 32);
        desktopProxy = new ProxyUnmodifiableDesktopFactory().newDesktopProxy(desktop);
    }

    @Test
    public void getFieldFromProxyObject_ShouldReturnTrue() {
        assertTrue(desktopProxy.getFormFactor() != null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setFieldToProxyClass_ShouldTrowException() {
        desktopProxy.setFormFactor("EX");
    }

}