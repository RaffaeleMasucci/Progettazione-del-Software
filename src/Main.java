import Controller.CartManagementController;
import Controller.PaymentController;
import Controller.RegistrationController;
import Model.TransferObject.Bill;
import Model.DAOFactoryMethod.Bill.BillDAOFactory;
import Model.DAOFactoryMethod.CRUD;
import Model.DAOFactoryMethod.Item.ItemDAOFactory;
import Model.DAOFactoryMethod.RowCart.RowCartDAOFactory;
import Model.DAOFactoryMethod.Ticket.TicketDAOFactory;
import View.DashboardView;
import View.RegistrationView;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        RegistrationView registrationView = new RegistrationView();
        DashboardView dashboardView = new DashboardView();
        CRUD itemDAO = new ItemDAOFactory().getDAO();
        CRUD rowCartDAO = new RowCartDAOFactory().getDAO();
        CRUD billDAO = new BillDAOFactory().getDAO();
        CRUD ticketDAO = new TicketDAOFactory().getDAO();

        RegistrationController registrationController = new RegistrationController(registrationView, dashboardView, ticketDAO);
        CartManagementController cartManagementController = new CartManagementController(dashboardView, itemDAO, rowCartDAO);
        PaymentController paymentController = new PaymentController(dashboardView, registrationView, billDAO, rowCartDAO, ticketDAO, new Bill());

    }
}