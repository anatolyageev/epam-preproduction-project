package com.epam.anatolii.ageev.proxy.factory;

import com.epam.anatolii.ageev.domain.Desktop;

public interface ProxyFactory {
    Desktop newDesktopProxy(Desktop desktop);
}
