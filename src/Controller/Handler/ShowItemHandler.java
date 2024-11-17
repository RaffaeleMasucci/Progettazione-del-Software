package Controller.Handler;

import Model.DAOFactoryMethod.CRUD;
import Model.TransferObject.Item;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ShowItemHandler {

    public static void showItem(DefaultTableModel defaultTableModelItem, CRUD itemDAO){


        defaultTableModelItem.getDataVector().removeAllElements();
        defaultTableModelItem.fireTableDataChanged();


        try {
            //read from db the items into items table
            //itemDAO = (ItemDAO) new ItemDAOFactory().getDAO();
            List<Item> items = (List<Item>) itemDAO.list();

            for(Item item : items){
                Vector<Object> vector = new Vector<>();
                vector.add(item.getId());
                vector.add(item.getName());
                vector.add(item.getCategory());
                vector.add(item.getQuantity());
                vector.add(item.getPrice());
                defaultTableModelItem.addRow(vector);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
