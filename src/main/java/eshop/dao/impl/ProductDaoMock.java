package eshop.dao.impl;

import eshop.dao.ProductDao;
import eshop.dao.exception.DaoSystemException;
import eshop.dao.exception.NoSuchEntityException;
import eshop.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductDaoMock implements ProductDao{
    private final Map<Integer, Product> memory = new ConcurrentHashMap<>();

    public ProductDaoMock() {
        this.memory.put(1, new Product (1, "Bread"));
        this.memory.put(2, new Product (2, "Paper"));
        this.memory.put(3, new Product(3, "Sugar"));
     }

    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {


        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No product for id == '" + id +"', only for " + memory.keySet());
        }
        return memory.get(id);
    }

    public List<Product> selectAll() throws DaoSystemException {
        return new ArrayList<>(memory.values());
    }
}
