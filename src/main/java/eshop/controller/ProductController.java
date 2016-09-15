package eshop.controller;

import eshop.dao.ProductDao;
import eshop.dao.exception.DaoSystemException;
import eshop.dao.exception.NoSuchEntityException;
import eshop.dao.impl.ProductDaoMock;
import eshop.entity.Product;
import eshop.inject.DependencyInjectionServlet;
import eshop.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    //private ProductDao productDao = new ProductDaoMock();

    //refactoring
    @Inject("productDao")
    ProductDao productDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                Integer id = Integer.valueOf(idStr);
                Product model = productDao.selectById(id);
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                //OK
                req.getRequestDispatcher(PAGE_OK).forward(req, resp);
                return;
            } catch (NumberFormatException e) {
                //e.printStackTrace();
            } catch (NoSuchEntityException e) {
                //e.printStackTrace();
            } catch (DaoSystemException e) {
                //e.printStackTrace();
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
