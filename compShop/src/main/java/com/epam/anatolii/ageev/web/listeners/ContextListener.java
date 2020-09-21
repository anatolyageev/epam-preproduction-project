package com.epam.anatolii.ageev.web.listeners;

import com.epam.anatolii.ageev.captcha.CaptchaService;
import com.epam.anatolii.ageev.captcha.CaptchaServiceFactory;
import com.epam.anatolii.ageev.captcha.impl.RemoveOldCaptcha;
import com.epam.anatolii.ageev.domain.User;
import com.epam.anatolii.ageev.repository.UserRepository;
import com.epam.anatolii.ageev.repository.impl.UserRepositoryDbImpl;
import com.epam.anatolii.ageev.repository.impl.UserRepositoryImpl;
import com.epam.anatolii.ageev.services.UserService;
import com.epam.anatolii.ageev.services.impl.UserServiceDbImpl;
import com.epam.anatolii.ageev.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.epam.anatolii.ageev.constants.WebConstant.*;

@WebListener
public class ContextListener implements ServletContextListener {
    final static Logger LOG = Logger.getLogger(ContextListener.class);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.debug("initialization started.");
        String captchaTime = servletContextEvent.getServletContext().getInitParameter(CAPTCHA_TIME_OUT);
        LOG.debug("Captcha timeout: " + captchaTime);
        ServletContext servletContext = servletContextEvent.getServletContext();
        UserRepository userRepository = new UserRepositoryDbImpl();
        UserService userService = new UserServiceDbImpl(userRepository);
        servletContext.setAttribute(USER_SERVICE, userService);
        CaptchaService captchaService = CaptchaServiceFactory.getCaptchaService(servletContextEvent.getServletContext());
        RemoveOldCaptcha removeOldCaptcha = new RemoveOldCaptcha(captchaService, Long.parseLong(captchaTime));
        scheduledExecutorService.scheduleWithFixedDelay(removeOldCaptcha, 0, CAPTCHA_TIME_OUT_JOB_TIMEOUT, TimeUnit.MILLISECONDS);
        servletContext.setAttribute(CAPTCHA_STRATEGY, captchaService);
//        initUsers(userService);

        LOG.debug("initialization finished.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.debug("Servlet context destruction starts");

        LOG.debug("Servlet context destruction finished");
    }

//    private void initUsers(UserService userService) {
//        User user1 = new User("firstUser", "First", "First", "qwer@rty.vdc", "qazWsx3edc");
//        User user2 = new User("secondUser", "second", "First", "qwer@rty.vdc", "qazWsx3edc");
//        User user3 = new User("theadUser", "thead", "First", "qwer@rty.vdc", "qazWsx3edc");
//        User user4 = new User("fourthUser", "fourth", "First", "qwer@rty.vdc", "qazWsx3edc");
//        userService.createUser(user1);
//        userService.createUser(user2);
//        userService.createUser(user3);
//        userService.createUser(user4);
//    }
}
