package Model.DAOFactoryMethod.Ticket;

import Model.DAOFactoryMethod.CRUD;
import Model.DAOFactoryMethod.DAOFactory;

//concrete creator
public class TicketDAOFactory extends DAOFactory {
    @Override
    public CRUD getDAO() {
        return new TicketDAO();
    }
}
