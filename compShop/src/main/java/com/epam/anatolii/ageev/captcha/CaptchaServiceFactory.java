package com.epam.anatolii.ageev.captcha;

import com.epam.anatolii.ageev.captcha.impl.CookieCaptchaServiceImpl;
import com.epam.anatolii.ageev.captcha.impl.HiddenFieldCaptchaServiceImpl;
import com.epam.anatolii.ageev.captcha.impl.SessionCaptchaServiceImpl;

import javax.servlet.ServletContext;

import static com.epam.anatolii.ageev.constants.WebConstant.*;

public class CaptchaServiceFactory {

    public static CaptchaService getCaptchaService(ServletContext context) {
        String captchaServiceType = context.getInitParameter(CAPTCHA_TYPE);
        if (captchaServiceType.equals(COOKIE_CONST)) {
            return new CookieCaptchaServiceImpl();
        }
        if (captchaServiceType.equals(FIELD_CONST)) {
            return new HiddenFieldCaptchaServiceImpl();
        }
        if (captchaServiceType.equals(SESSION_CONST)) {
            return new SessionCaptchaServiceImpl();
        }
        return new HiddenFieldCaptchaServiceImpl();
    }
}
