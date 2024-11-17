package Model.DAOFactoryMethod.RowCart;

import Model.DAOFactoryMethod.CRUD;
import Model.DAOFactoryMethod.DAOFactory;

//concrete creator
public class RowCartDAOFactory extends DAOFactory {
    @Override
    public CRUD getDAO() {
        return new RowCartDAO();
    }
}
