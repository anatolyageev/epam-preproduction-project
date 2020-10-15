package com.epam.anatolii.ageev.web.filter;

import com.epam.anatolii.ageev.domain.User;
import com.epam.anatolii.ageev.services.UserService;
import com.epam.anatolii.ageev.web.filter.service.AccessService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


import static com.epam.anatolii.ageev.constants.WebConstant.ACCESS_SERVICE;
import static com.epam.anatolii.ageev.constants.WebConstant.FROM_URL;
import static com.epam.anatolii.ageev.constants.WebConstant.REGISTRATION_URL;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_ID;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_SERVICE;

public class UserFilter implements Filter {
    final static Logger LOG = Logger.getLogger(UserFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession httpSession = httpServletRequest.getSession();
        String userLogin = (String) httpSession.getAttribute(USER_ID);
        String requestUrl = httpServletRequest.getRequestURI();
        AccessService accessService = (AccessService) httpServletRequest.getServletContext().getAttribute(ACCESS_SERVICE);

        if (!accessService.isUrlPermitted(requestUrl)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        if (Objects.isNull(userLogin)) {
            httpSession.setAttribute(FROM_URL, requestUrl);
            httpServletResponse.sendRedirect(REGISTRATION_URL);
            return;
        }

        UserService userService = (UserService) httpServletRequest.getServletContext().getAttribute(USER_SERVICE);
        User user = userService.getOne(userLogin);

        if (accessService.isAccessible(user.getUserRole(), requestUrl)) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
