package eshop.controller;

import eshop.dao.ProductDao;
import eshop.dao.exception.DaoSystemException;
import eshop.dao.impl.ProductDaoMock;
import eshop.entity.Product;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductAllController extends HttpServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "productList";
    public static final String PAGE_OK = "productAll.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    private ProductDao productDao = new ProductDaoMock();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> model = productDao.selectAll();
            request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            //OK
            RequestDispatcher dispatcher = request.getRequestDispatcher(PAGE_OK);
            dispatcher.forward(request, response);
            return;
        } catch (DaoSystemException ignore) {
            /*NOP*/
        }
        //FAIL
        response.sendRedirect(PAGE_ERROR);

    }
}
