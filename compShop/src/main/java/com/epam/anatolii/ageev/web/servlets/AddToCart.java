package com.epam.anatolii.ageev.web.servlets;

import com.epam.anatolii.ageev.domain.Cart;
import com.epam.anatolii.ageev.domain.Product;
import com.epam.anatolii.ageev.services.ProductService;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;


import static com.epam.anatolii.ageev.constants.WebConstant.CART;
import static com.epam.anatolii.ageev.constants.WebConstant.PRODUCT_COUNT_CART;
import static com.epam.anatolii.ageev.constants.WebConstant.PRODUCT_ID;
import static com.epam.anatolii.ageev.constants.WebConstant.PRODUCT_SERVICE;
import static com.epam.anatolii.ageev.constants.sql.Fields.ENTITY_ID_PRODUCTS;
import static com.epam.anatolii.ageev.constants.sql.Fields.PRODUCT_COUNT;

@WebServlet("/addToCart")
public class AddToCart extends HttpServlet {
    final static Logger LOG = Logger.getLogger(AddToCart.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       HttpSession session = req.getSession();
        Cart cart = (Cart)Optional.ofNullable(session.getAttribute(CART)).orElse(new Cart());
        LOG.debug("Cart: " + cart);
        ProductService productService = (ProductService) req.getServletContext().getAttribute(PRODUCT_SERVICE);
        PrintWriter pw = resp.getWriter();
        JsonObject jsonObject = new JsonObject();
        String productId = req.getParameter(PRODUCT_ID);
     //   String productCount = req.getParameter(PRODUCT_COUNT_CART);
        LOG.debug("ProductID: " + productId);
        if(Objects.isNull(productId)){
            pw.write(jsonObject.toString());
            pw.close();
            return;
        }

        Product addedProduct = productService.getOne(Long.parseLong(productId));
        LOG.debug("eaquals; " + productService.getOne(1L).equals(productService.getOne(1L)) );

        if(cart.isProductPresent(addedProduct)){
            Integer currentAmount = cart.getAmountOfProduct(addedProduct);
            cart.addProductToCart(addedProduct,++currentAmount);
        }else {
            cart.addProductToCart(addedProduct, 1);
        }
        session.setAttribute(CART, cart);
        session.setAttribute(PRODUCT_COUNT_CART,cart.getProductsNumber());
        jsonObject.addProperty(PRODUCT_COUNT_CART,cart.getProductsNumber());
        LOG.debug("jsonObj: " + jsonObject.toString());
        pw.write(jsonObject.toString());
        pw.close();
    }

}
