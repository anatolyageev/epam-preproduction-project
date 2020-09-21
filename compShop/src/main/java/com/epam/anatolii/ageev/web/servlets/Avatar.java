package com.epam.anatolii.ageev.web.servlets;

import com.epam.anatolii.ageev.web.utils.AvatarsUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static com.epam.anatolii.ageev.constants.WebConstant.*;

@WebServlet("/getAvatar")
public class Avatar extends HttpServlet {
    final static Logger LOG = Logger.getLogger(Avatar.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug(getClass() + " doGet() started");
        String currentUserLogin = (String) req.getSession().getAttribute(LOGIN_USER);
        LOG.debug("Current user: " + currentUserLogin);
        File avatarFile = new File(AvatarsUtils.getServerPath() + currentUserLogin + AVATAR_PNG_EXTENSION);
        LOG.debug("Path retreave avatar: " + AvatarsUtils.getServerPath() + currentUserLogin + AVATAR_PNG_EXTENSION);
        ImageIO.write(ImageIO.read(avatarFile), AVATAR_PNG_FORMAT_NAME, resp.getOutputStream());
        LOG.debug(getClass() + " doGet() finished");
    }
}
