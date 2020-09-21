package com.epam.anatolii.ageev.web.servlets;

import com.epam.anatolii.ageev.domain.User;
import com.epam.anatolii.ageev.services.UserService;
import com.epam.anatolii.ageev.web.utils.LoginUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.epam.anatolii.ageev.constants.WebConstant.*;


@WebServlet("/login")
public class Login extends HttpServlet {
    final static Logger LOG = Logger.getLogger(Login.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug(getClass() + " doGet() started");
        req.getRequestDispatcher(req.getHeader("Referer")).forward(req,resp);
        LOG.debug(getClass() + " doGet() ended");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug(getClass() + " doPost() started");
        Map<String, String> errors = LoginUtils.checkLogin(req);
        if(!errors.isEmpty()){
            req.getSession().setAttribute(LOGIN_ERROR,"Login and password does not correct");
            resp.sendRedirect("login");
            return;
        }

        String login = req.getParameter(USER_ID);
        String password = req.getParameter(USER_PASSWORD);
        LOG.debug("Url form request ==> " + req.getRequestURL());
        LOG.debug("Params from reqiest ==> " + login +"; "+ password);
        UserService userService = (UserService) req.getServletContext().getAttribute(USER_SERVICE);
        if(userService.checkUserExistInDb(login)){
            User user = userService.getOne(login);
            LOG.debug("User from db ==> " + user);
            if(Objects.equals(user.getPassword(),password)) {
                req.getSession().setAttribute(LOGIN_USER, login);
                resp.sendRedirect(req.getHeader("Referer"));
                return;
            }
        }
        resp.sendRedirect(req.getHeader("Referer"));
        LOG.debug(getClass() + " doPost() finished");
    }
}
