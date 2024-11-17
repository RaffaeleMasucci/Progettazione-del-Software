package Model.DAOFactoryMethod.Bill;

import Model.DAOFactoryMethod.CRUD;
import Model.DAOFactoryMethod.DAOFactory;

public class BillDAOFactory extends DAOFactory {
    @Override
    public CRUD getDAO() {
        return new BillDAO();
    }
}
