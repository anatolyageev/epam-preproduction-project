package com.epam.anatolii.ageev.web.servlets;

import com.epam.anatolii.ageev.captcha.impl.SessionCaptchaServiceImpl;
import com.epam.anatolii.ageev.domain.UserFromForm;
import com.epam.anatolii.ageev.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.epam.anatolii.ageev.constants.WebConstant.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationTest {

    @Mock
    private ServletConfig servletConfig;
    @Mock
    private HttpSession session;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ServletContext servletContext;
    @Mock
    private SessionCaptchaServiceImpl captchaService;
    @Mock
    private UserServiceImpl userService;
    private UserFromForm userFromForm;
    private UserFromForm userFromForm1;
    private Map<Long, String> captchaMap;

    @Before
    public void before() throws ServletException {
        userFromForm = new UserFromForm("login8JK", "Petr", "Petrov", "mail@mail.com", "qaz2wSX3ed", "1qaz2wsx");
        userFromForm1 = new UserFromForm("login8JK", "Petr", "Petrov", "mail@mail.com", "qaz2wSX3ed", "incorrect");
        captchaMap = new ConcurrentHashMap<>();
        captchaMap.put(123456L, "1qaz2wsx");

        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(CAPTCHA_STRATEGY)).thenReturn(captchaService);
        when(request.getSession()).thenReturn(session);
        when(captchaService.getCaptchaMap()).thenReturn(captchaMap);
        when(captchaService.getTimeCreatedCaptcha(request)).thenReturn(123456L);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(USER_SERVICE)).thenReturn(userService);
    }

    @Test
    public void shouldReturnToRegistrationPage() throws ServletException, IOException {
        when(request.getParameter(USER_ID)).thenReturn(userFromForm1.getLogin());
        when(request.getParameter(USER_NAME)).thenReturn(userFromForm1.getFirstName());
        when(request.getParameter(USER_LAST_NAME)).thenReturn(userFromForm1.getLastName());
        when(request.getParameter(USER_EMAIL)).thenReturn(userFromForm1.getEmail());
        when(request.getParameter(USER_PASSWORD)).thenReturn(userFromForm1.getPassword());
        when(request.getParameter(USER_CAPTCHA)).thenReturn(userFromForm1.getUserCaptcha());

        Registration registration = new Registration();
        registration.init(servletConfig);
        registration.doPost(request, response);
        verify(response).sendRedirect("registration");
    }

    @Test
    public void shouldReturnToHomePage_CorrectValues() throws ServletException, IOException {
        when(request.getParameter(USER_ID)).thenReturn(userFromForm.getLogin());
        when(request.getParameter(USER_NAME)).thenReturn(userFromForm.getFirstName());
        when(request.getParameter(USER_LAST_NAME)).thenReturn(userFromForm.getLastName());
        when(request.getParameter(USER_EMAIL)).thenReturn(userFromForm.getEmail());
        when(request.getParameter(USER_PASSWORD)).thenReturn(userFromForm.getPassword());
        when(request.getParameter(USER_CAPTCHA)).thenReturn(userFromForm.getUserCaptcha());

        Registration registration = new Registration();
        registration.init(servletConfig);
        registration.doPost(request, response);
        verify(response).sendRedirect("index.jsp");
    }
}