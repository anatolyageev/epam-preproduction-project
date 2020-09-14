package com.epam.anatolii.ageev.captcha.impl;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.epam.anatolii.ageev.constants.WebConstant.CAPTCHA_TIME_CREATED;
import static com.epam.anatolii.ageev.constants.WebConstant.CAPTCHA_VALUE;
import static java.lang.System.currentTimeMillis;

public class SessionCaptchaServiceImpl extends AbstractCaptchaServiceImpl {
    final static Logger LOG = Logger.getLogger(SessionCaptchaServiceImpl.class);

    @Override
    public void initCaptchaValue(HttpServletRequest request, HttpServletResponse response) {
        Long timeGenerated = currentTimeMillis();
        String captchaValue = generateCaptchaValue();
        captchaMap.put(timeGenerated, captchaValue);
        request.getSession().setAttribute(CAPTCHA_VALUE, captchaValue);
        request.getSession().setAttribute(CAPTCHA_TIME_CREATED, timeGenerated);
        LOG.debug(getClass() + " captcha init to session " + captchaValue);
    }

    @Override
    public String getCaptchaValue(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(CAPTCHA_VALUE);
    }

    @Override
    public Long getTimeCreatedCaptcha(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(CAPTCHA_TIME_CREATED);
    }
}
