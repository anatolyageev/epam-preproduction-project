package com.epam.anatolii.ageev.web.utils;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.epam.anatolii.ageev.constants.WebConstant.USER_AVATAR;

public class AvatarsUtils {
    final static Logger LOG = Logger.getLogger(AvatarsUtils.class);
    private static String serverPath;

    private static final String AVATAR_FOLDER = File.separator + "avatars" + File.separator;

    private static void checkPath() {
        serverPath = System.getProperty("catalina.home") + AVATAR_FOLDER;
        File path = new File(serverPath);
        if (!path.exists()) {
            path.mkdir();
        }
    }

    public static String getServerPath() {
        checkPath();
        return serverPath;
    }

    public static void saveAvatar(HttpServletRequest request, String login) throws IOException, ServletException {
        checkPath();
        final String saveAvatarPath = serverPath + login + ".png";
        LOG.debug("File save path: " + saveAvatarPath);
        Part avatarFile = request.getPart(USER_AVATAR);
        try (InputStream inputStream = avatarFile.getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            ImageIO.write(bufferedImage, "png", new File(saveAvatarPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
