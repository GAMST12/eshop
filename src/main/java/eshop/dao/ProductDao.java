package eshop.dao;


import eshop.dao.exception.DaoSystemException;
import eshop.dao.exception.NoSuchEntityException;
import eshop.entity.Product;

import java.util.List;

public interface ProductDao {
    public Product selectById (int id) throws DaoSystemException, NoSuchEntityException;

    public List<Product> selectAll() throws DaoSystemException;
}
