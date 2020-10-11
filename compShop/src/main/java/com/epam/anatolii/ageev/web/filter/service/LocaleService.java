package com.epam.anatolii.ageev.web.filter.service;

import java.util.Locale;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LocaleService {
    void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale);

    String getLocale(HttpServletRequest httpServletRequest);
}
