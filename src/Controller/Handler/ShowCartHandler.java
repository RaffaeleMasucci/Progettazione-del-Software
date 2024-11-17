package Controller.Handler;

import Model.DAOFactoryMethod.CRUD;
import Model.TransferObject.RowCart;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ShowCartHandler {

    public static void showCart(int ticketID, DefaultTableModel defaultTableModelCart, CRUD rowCartDAO){

        List<RowCart> cart;

        defaultTableModelCart.getDataVector().removeAllElements();
        defaultTableModelCart.fireTableDataChanged();

        try {
            //read from DB the cart_table and update the table cart
           //rowCartDAO = (RowCartDAO) new RowCartDAOFactory().getDAO();
           cart = (List<RowCart>) rowCartDAO.list();

            for(RowCart rowCart : cart){

                if(ticketID == rowCart.getTicketID()) {
                    Vector<Object> vector = new Vector<>();
                    //vector.add(rowCart.getTicketID());
                    vector.add(rowCart.getRowNumber());
                    vector.add(rowCart.getItemID());
                    vector.add(rowCart.getItemName());
                    vector.add(rowCart.getItemCategory());
                    vector.add(rowCart.getItemQuantity());
                    vector.add(rowCart.getItemPrice());
                    vector.add(rowCart.getTotalAmount());
                    defaultTableModelCart.addRow(vector);

                }


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }

