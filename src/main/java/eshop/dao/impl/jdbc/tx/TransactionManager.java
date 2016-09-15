package eshop.dao.impl.jdbc.tx;

public interface TransactionManager {
    public <T, E extends Exception> T doInTransaction(UnitOfWork<T,E> unitOfWork) throws E;
}
