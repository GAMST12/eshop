package eshop.dao.impl.jdbc.tx;

public interface UnitOfWork<T, E extends  Exception> {
    public T doInTx() throws E;
}
