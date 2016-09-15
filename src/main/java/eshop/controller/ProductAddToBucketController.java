package eshop.controller;

import eshop.dao.ProductDao;
import eshop.dao.exception.DaoSystemException;
import eshop.dao.exception.NoSuchEntityException;
import eshop.dao.impl.ProductDaoMock;
import eshop.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductAddToBucketController extends HttpServlet {
    public static final String PARAM_ID = "id";
    public static final String PAGE_ERROR = "productAll.do";

    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Product product = productDao.selectById(id);

                HttpSession session = request.getSession(true);
                Map<Product, Integer> oldBucket = (Map <Product, Integer>) session.getAttribute(SessionAttributes.PRODUCTS_IN_BUCKET);
                if (oldBucket == null) {
                    session.setAttribute(SessionAttributes.PRODUCTS_IN_BUCKET, Collections.singletonMap(product, 1));
                } else {
                    Map<Product, Integer> newBucket = new LinkedHashMap<Product, Integer>(oldBucket);
                    if (!oldBucket.containsKey(product)) {
                        newBucket.put(product, 1);
                    } else {
                        newBucket.put(product, newBucket.get(product) + 1);
                    }
                    session.setAttribute(SessionAttributes.PRODUCTS_IN_BUCKET, Collections.unmodifiableMap(newBucket));
                }
                //OK
                String newLocation = "product.do?id=" + id;
                response.sendRedirect(newLocation);
                return;
            } catch (NoSuchEntityException e) {
                e.printStackTrace();
            } catch (DaoSystemException e) {
                e.printStackTrace();
            }
        }
    }
}
