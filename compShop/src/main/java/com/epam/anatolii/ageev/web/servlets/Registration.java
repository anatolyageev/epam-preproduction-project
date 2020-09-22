package com.epam.anatolii.ageev.web.servlets;

import com.epam.anatolii.ageev.captcha.CaptchaService;
import com.epam.anatolii.ageev.domain.UserFromForm;
import com.epam.anatolii.ageev.services.UserService;
import com.epam.anatolii.ageev.web.utils.AvatarsUtils;
import com.epam.anatolii.ageev.web.utils.LoginUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.epam.anatolii.ageev.constants.WebConstant.CAPTCHA_STRATEGY;
import static com.epam.anatolii.ageev.constants.WebConstant.REGISTER_ERROR;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_EMAIL;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_ID;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_LAST_NAME;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_NAME;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_SERVICE;

@WebServlet("/registration")
@MultipartConfig
public class Registration extends HttpServlet {
    final static Logger LOG = Logger.getLogger(Registration.class);
    CaptchaService captchaService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.captchaService = (CaptchaService) config.getServletContext().getAttribute(CAPTCHA_STRATEGY);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug(getClass() + " doGet() started");

        captchaService.initCaptchaValue(req, resp);
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);

        LOG.debug(getClass() + " doGet() ended");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug(getClass() + " doPost() started");
        UserFromForm userFromForm = LoginUtils.getUserFromForm(req);
        Map<String, String> errors = LoginUtils.checkForm(userFromForm);

        if (!LoginUtils.isCaptchaValid(captchaService, userFromForm.getUserCaptcha(), req)) {
            putValuesToAttributes(req, userFromForm);
            resp.sendRedirect("registration");
            return;
        }

        UserService userService = (UserService) req.getServletContext().getAttribute(USER_SERVICE);
        if (!errors.isEmpty()) {
            putValuesToAttributes(req, userFromForm);
            req.getSession().setAttribute(REGISTER_ERROR, "Inputs not correct");
            resp.sendRedirect("registration");
            return;
        }

        if (userService.checkUserExistInDb(userFromForm.getLogin())) {
            String userId = userFromForm.getLogin();
            LOG.debug("userId: " + userId);
            putValuesToAttributes(req, userFromForm);
            req.getSession().setAttribute(REGISTER_ERROR, "User id already exists!");
            resp.sendRedirect("registration");
            return;
        }

        userService.createUser(userFromForm.getUser());
        AvatarsUtils.saveAvatar(req, userFromForm.getLogin());
        emptyValuesAndErrors(req);
        resp.sendRedirect("index.jsp");

        LOG.debug(getClass() + " doPost() ended");
    }

    private void putValuesToAttributes(HttpServletRequest req, UserFromForm userFromForm) {
        req.getSession().setAttribute(USER_ID + "_value", userFromForm.getLogin());
        req.getSession().setAttribute(USER_NAME + "_value", userFromForm.getFirstName());
        req.getSession().setAttribute(USER_LAST_NAME + "_value", userFromForm.getLastName());
        req.getSession().setAttribute(USER_EMAIL + "_value", userFromForm.getEmail());
    }

    private void emptyValuesAndErrors(HttpServletRequest req) {
        req.getSession().removeAttribute(USER_ID + "_value");
        req.getSession().removeAttribute(USER_NAME + "_value");
        req.getSession().removeAttribute(USER_LAST_NAME + "_value");
        req.getSession().removeAttribute(USER_EMAIL + "_value");
        req.getSession().removeAttribute(REGISTER_ERROR);
    }
}
