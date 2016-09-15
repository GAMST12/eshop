package eshop.controller;

import eshop.dao.ProductDao;
import eshop.dao.exception.DaoException;
import eshop.dao.exception.DaoSystemException;
import eshop.dao.exception.NoSuchEntityException;
import eshop.dao.impl.jdbc.tx.TransactionManager;
import eshop.dao.impl.jdbc.tx.UnitOfWork;
import eshop.entity.Product;
import eshop.inject.DependencyInjectionServlet;
import eshop.inject.Inject;
import sun.util.resources.cldr.aa.CalendarData_aa_DJ;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ProductControllerExternalTx extends DependencyInjectionServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    //refactoring
    @Inject("txManager")
    TransactionManager txManager;

    @Inject("productDao")
    ProductDao productDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null) {
            try {
                final Integer id = Integer.valueOf(idStr);
                Product model = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    @Override
                    public Product doInTx() throws DaoException{
                        return productDao.selectById(id);
                    }
                });
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
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
