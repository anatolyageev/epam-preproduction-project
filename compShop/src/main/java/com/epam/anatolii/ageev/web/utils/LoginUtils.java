package com.epam.anatolii.ageev.web.utils;

import com.epam.anatolii.ageev.captcha.CaptchaService;
import com.epam.anatolii.ageev.domain.UserFromForm;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import static com.epam.anatolii.ageev.constants.Messages.*;
import static com.epam.anatolii.ageev.constants.WebConstant.*;

public class LoginUtils {
    public static final String REGEX_USER_ID = "^(?!\\d+$)\\w{8,20}";
    public static final String REGEX_NAME = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
    public static final String REGEX_MAIL = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})";
    public static final String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}";
    final static Logger LOG = Logger.getLogger(LoginUtils.class);

    public static UserFromForm getUserFromForm(HttpServletRequest request) {
        return new UserFromForm(request.getParameter(USER_ID),
                request.getParameter(USER_NAME),
                request.getParameter(USER_LAST_NAME),
                request.getParameter(USER_EMAIL),
                request.getParameter(USER_PASSWORD),
                request.getParameter(USER_CAPTCHA));
    }

    public static Map<String, String> checkForm(UserFromForm userFromForm) {
        Map<String, String> errors = new HashMap<>();

        if (Objects.isNull(userFromForm.getLogin()) || !userFromForm.getLogin().matches(REGEX_USER_ID)) {
            errors.put(USER_ID, INVALID_USER_ID);
        }
        if (Objects.isNull(userFromForm.getFirstName()) || !userFromForm.getFirstName().matches(REGEX_NAME)) {
            errors.put(USER_NAME, INVALID_USER_NAME);
        }
        if (Objects.isNull(userFromForm.getLastName()) || !userFromForm.getLastName().matches(REGEX_NAME)) {
            errors.put(USER_LAST_NAME, INVALID_USER_LAST_NAME);
        }
        if (Objects.isNull(userFromForm.getEmail()) || !userFromForm.getEmail().matches(REGEX_MAIL)) {
            errors.put(USER_EMAIL, INVALID_USER_MAIL);
        }
        if (Objects.isNull(userFromForm.getPassword()) || !userFromForm.getPassword().matches(REGEX_PASSWORD)) {
            errors.put(USER_PASSWORD, INVALID_USER_PASSWORD);
        }
        return errors;
    }

    public static boolean isCaptchaValid(CaptchaService captchaService, String captcha, HttpServletRequest req) {
        boolean result = true;
        Map<Long, String> captchaMap = captchaService.getCaptchaMap();
        String error = "";
        if (captchaMap.containsKey(captchaService.getTimeCreatedCaptcha(req))) {
            LOG.debug("Time from servise: " + captchaService.getTimeCreatedCaptcha(req));

            captchaMap.entrySet().stream().forEach(es -> {
                LOG.debug("Time from map:" + es.getKey() + " || " + es.getValue());
            });

            result = captcha.equals(captchaMap.get(captchaService.getTimeCreatedCaptcha(req)));
            if (!result) {
                error = INVALID_USER_CAPTCHA_VALUE;
            }
        } else {
            error = TIMEOUT_USER_CAPTCHA;
            result = false;
        }
        if (!result) {
            req.getSession().setAttribute(REGISTER_ERROR, error);
        }
        return result;
    }

    /**
     * Generates a PNG image of text 180 pixels wide, 40 pixels high with white background.
     *
     * @param text expects string size eight (8) characters.
     * @return byte array that is a PNG image generated with text displayed.
     */
    public static byte[] generateImage(String text) {
        int w = 180, h = 40;
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(Color.white);
        g.fillRect(0, 0, w, h);
        g.setFont(new Font("Serif", Font.PLAIN, 26));
        g.setColor(Color.blue);
        int start = 10;
        byte[] bytes = text.getBytes();

        Random random = new Random();
        for (int i = 0; i < bytes.length; i++) {
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            g.drawString(new String(new byte[]{bytes[i]}), start + (i * 20), (int) (Math.random() * 20 + 20));
        }
        g.setColor(Color.white);
        for (int i = 0; i < 8; i++) {
            g.drawOval((int) (Math.random() * 160), (int) (Math.random() * 10), 30, 30);
        }
        g.dispose();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", bout);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bout.toByteArray();
    }
}