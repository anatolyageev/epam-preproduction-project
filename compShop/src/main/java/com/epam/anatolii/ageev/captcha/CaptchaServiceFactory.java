package com.epam.anatolii.ageev.captcha;

import com.epam.anatolii.ageev.captcha.impl.CookieCaptchaServiceImpl;
import com.epam.anatolii.ageev.captcha.impl.HiddenFieldCaptchaServiceImpl;
import com.epam.anatolii.ageev.captcha.impl.SessionCaptchaServiceImpl;

import javax.servlet.ServletContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.epam.anatolii.ageev.constants.WebConstant.*;

public class CaptchaServiceFactory {
    private static Map<String,CaptchaService> captchaServiceMap;
    static{
        captchaServiceMap = new ConcurrentHashMap<>();
        captchaServiceMap.put(COOKIE_CONST, new CookieCaptchaServiceImpl());
        captchaServiceMap.put(FIELD_CONST, new HiddenFieldCaptchaServiceImpl());
        captchaServiceMap.put(SESSION_CONST, new SessionCaptchaServiceImpl());
    }

    public static CaptchaService getCaptchaService(ServletContext context) {
        String captchaServiceType = context.getInitParameter(CAPTCHA_TYPE);
        return captchaServiceMap.get(captchaServiceType);
    }
}
