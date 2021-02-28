package com.epam.anatolii.ageev.web.listeners;

import com.epam.anatolii.ageev.captcha.CaptchaService;
import com.epam.anatolii.ageev.captcha.CaptchaServiceFactory;
import com.epam.anatolii.ageev.captcha.impl.RemoveOldCaptcha;
import com.epam.anatolii.ageev.repository.OrderRepository;
import com.epam.anatolii.ageev.repository.ProductRepository;
import com.epam.anatolii.ageev.repository.UserRepository;
import com.epam.anatolii.ageev.repository.impl.OrderRepositoryImpl;
import com.epam.anatolii.ageev.repository.impl.ProductRepositoryImpl;
import com.epam.anatolii.ageev.repository.impl.UserRepositoryDbImpl;
import com.epam.anatolii.ageev.services.OrderService;
import com.epam.anatolii.ageev.services.ProductService;
import com.epam.anatolii.ageev.services.UserService;
import com.epam.anatolii.ageev.services.impl.OrderServiceImpl;
import com.epam.anatolii.ageev.services.impl.ProductServiceImpl;
import com.epam.anatolii.ageev.services.impl.UserServiceDbImpl;
import com.epam.anatolii.ageev.web.filter.service.AccessService;
import com.epam.anatolii.ageev.web.filter.service.impl.AccessServiceImpl;
import com.epam.anatolii.ageev.web.utils.XmlUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import org.apache.log4j.Logger;


import static com.epam.anatolii.ageev.constants.DbConnectionConstants.DATA_SOURCE;
import static com.epam.anatolii.ageev.constants.DbConnectionConstants.INIT_CONTEXT_LOOKUP;
import static com.epam.anatolii.ageev.constants.WebConstant.ACCESS_SERVICE;
import static com.epam.anatolii.ageev.constants.WebConstant.CAPTCHA_STRATEGY;
import static com.epam.anatolii.ageev.constants.WebConstant.CAPTCHA_TIME_OUT;
import static com.epam.anatolii.ageev.constants.WebConstant.CAPTCHA_TIME_OUT_JOB_TIMEOUT;
import static com.epam.anatolii.ageev.constants.WebConstant.ORDER_SERVICE;
import static com.epam.anatolii.ageev.constants.WebConstant.PRODUCT_SERVICE;
import static com.epam.anatolii.ageev.constants.WebConstant.SECURITY_FILE;
import static com.epam.anatolii.ageev.constants.WebConstant.SECURITY_MAP;
import static com.epam.anatolii.ageev.constants.WebConstant.USER_SERVICE;

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
        Map<String, List<String>> securityMap = XmlUtils.securityXMLParse(servletContext.getInitParameter(SECURITY_FILE));
        AccessService accessService = new AccessServiceImpl(securityMap);
        UserRepository userRepository = new UserRepositoryDbImpl();
        ProductRepository productRepository = new ProductRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        DataSource dataSource = getDataSource();
        UserService userService = new UserServiceDbImpl(userRepository, dataSource);
        ProductService productService = new ProductServiceImpl(productRepository, dataSource);
        OrderService orderService = new OrderServiceImpl(orderRepository, dataSource);
        servletContext.setAttribute(USER_SERVICE, userService);
        servletContext.setAttribute(PRODUCT_SERVICE, productService);
        servletContext.setAttribute(ORDER_SERVICE, orderService);
        servletContext.setAttribute(ACCESS_SERVICE, accessService);
        servletContext.setAttribute(SECURITY_MAP, securityMap);
        CaptchaService captchaService = CaptchaServiceFactory.getCaptchaService(servletContextEvent.getServletContext());
        RemoveOldCaptcha removeOldCaptcha = new RemoveOldCaptcha(captchaService, Long.parseLong(captchaTime));
        scheduledExecutorService.scheduleWithFixedDelay(removeOldCaptcha, 0, CAPTCHA_TIME_OUT_JOB_TIMEOUT, TimeUnit.MILLISECONDS);
        servletContext.setAttribute(CAPTCHA_STRATEGY, captchaService);
        LOG.debug("initialization finished.");
    }

    private DataSource getDataSource() {
        DataSource dataSource = null;
        try {
            Context initContext = (Context) new InitialContext().lookup(INIT_CONTEXT_LOOKUP);
            dataSource = (DataSource) initContext.lookup(DATA_SOURCE);
            LOG.debug("Initialization datasource success: " + dataSource.toString());
        } catch (NamingException e) {
            LOG.error("Data source error " + e);
        }
        return dataSource;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.debug("Servlet context destruction starts");

        LOG.debug("Servlet context destruction finished");
    }
}
