package com.epam.anatolii.ageev.web.filter.service.impl;

import com.epam.anatolii.ageev.web.filter.service.LocaleService;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.epam.anatolii.ageev.constants.WebConstant.LOCALE;

public class LocaleServiceSessionImpl implements LocaleService {
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
        httpServletRequest.getSession().setAttribute(LOCALE, locale.toString());
    }

    @Override
    public String getLocale(HttpServletRequest httpServletRequest) {
        return (String) httpServletRequest.getSession().getAttribute(LOCALE);
    }
}
