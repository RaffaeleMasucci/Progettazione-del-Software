package Model.DAOFactoryMethod.Item;

import Model.DAOFactoryMethod.CRUD;
import Model.DAOFactoryMethod.DAOFactory;

//concrete creator
public class ItemDAOFactory extends DAOFactory {

    @Override
    public CRUD getDAO() { return new ItemDAO(); }

}
