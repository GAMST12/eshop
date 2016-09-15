package eshop.controller;

import eshop.dao.ProductDao;
import eshop.dao.exception.DaoSystemException;
import eshop.dao.exception.NoSuchEntityException;
import eshop.entity.Product;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import eshop.controller.ProductController;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class ProductControllerTest {
    public ProductController controller;

    @Before
    public void setUp() {
        this.controller = new ProductController();
    }

    @Test
    public void test_no_param() throws ServletException, IOException {
        //init
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter(ProductController.PARAM_ID)).thenReturn(null);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //use

        controller.doGet(request, response);

        //check
        verify(request).getParameter(ProductController.PARAM_ID);
        verify(response).sendRedirect(ProductController.PAGE_ERROR);
        verifyNoMoreInteractions(request, response);
    }

    @Test
    public void test_bad_param() throws ServletException, IOException {
        //init
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter(ProductController.PARAM_ID)).thenReturn("abc"); //product.do?id=abc


        //use

        controller.doGet(request, response);

        //check
        verify(request).getParameter(ProductController.PARAM_ID);
        verify(response).sendRedirect(ProductController.PAGE_ERROR);
        verifyNoMoreInteractions(request, response);
    }

    @Test
    public void test_no_in_dao() throws ServletException, IOException, DaoSystemException, NoSuchEntityException {
        //init
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ProductDao  dao = mock(ProductDao.class);


        when(request.getParameter(ProductController.PARAM_ID)).thenReturn("123"); //product.do?id=123
        controller.productDao = dao;
        when(dao.selectById(anyInt())).thenThrow(new NoSuchEntityException(""));

        //use

        controller.doGet(request, response);

        //check
        verify(request).getParameter(ProductController.PARAM_ID);
        verify(response).sendRedirect(ProductController.PAGE_ERROR);
        verify(dao).selectById(123);
        verifyNoMoreInteractions(request, response, dao);
    }

    @Test
    public void test_dao_crashed() throws ServletException, IOException, DaoSystemException, NoSuchEntityException {
        //init
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ProductDao  dao = mock(ProductDao.class);


        when(request.getParameter(ProductController.PARAM_ID)).thenReturn("123"); //product.do?id=123
        controller.productDao = dao;
        when(dao.selectById(anyInt())).thenThrow(new DaoSystemException(""));

        //use

        controller.doGet(request, response);

        //check
        verify(request).getParameter(ProductController.PARAM_ID);
        verify(response).sendRedirect(ProductController.PAGE_ERROR);
        verify(dao).selectById(123);
        verifyNoMoreInteractions(request, response, dao);
    }


    @Test
    public void test_ok() throws ServletException, IOException, DaoSystemException, NoSuchEntityException {
        //init
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ProductDao  dao = mock(ProductDao.class);
        Product product = new Product(123,"Paper");



        when(request.getParameter(ProductController.PARAM_ID)).thenReturn("123"); //product.do?id=123
        when(request.getRequestDispatcher(ProductController.PAGE_OK)).thenReturn(dispatcher);
        controller.productDao = dao;
        when(dao.selectById(anyInt())).thenReturn(product);

        //use

        controller.doGet(request, response);

        //check
        verify(request).getParameter(ProductController.PARAM_ID);
        verify(dao).selectById(123);
        verify(request).setAttribute(ProductController.ATTRIBUTE_MODEL_TO_VIEW, product);
        verify(request).getRequestDispatcher(ProductController.PAGE_OK);
        verify(dispatcher).forward(request,response);
        verifyNoMoreInteractions(request, response, dispatcher, dao);
    }


}
