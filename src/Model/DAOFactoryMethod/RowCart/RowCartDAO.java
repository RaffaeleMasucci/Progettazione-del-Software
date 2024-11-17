package Model.DAOFactoryMethod.RowCart;

import Model.DAOFactoryMethod.CRUD;
import Model.DBConnection;
import Model.TransferObject.RowCart;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RowCartDAO implements CRUD {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    /**
     * @param object
     */
    @Override
    public void insert(Object object) throws SQLException {

        RowCart rowCart = (RowCart) object;

        String query = "INSERT INTO cart_table(ticket_id, item_id, item_name, item_category, quantity, unit_price, total_amount) " +
                "VALUES(?, ?, ? ,?, ?, ?, ?)";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, rowCart.getTicketID());
            preparedStatement.setInt(2, rowCart.getItemID());
            preparedStatement.setString(3, rowCart.getItemName());
            preparedStatement.setString(4, rowCart.getItemCategory());
            preparedStatement.setInt(5, rowCart.getItemQuantity());
            preparedStatement.setDouble(6, rowCart.getItemPrice());
            preparedStatement.setDouble(7, rowCart.getTotalAmount());
            int result = preparedStatement.executeUpdate();

            if(result > 0){
                JOptionPane.showMessageDialog(null, "Row Added Successfully to Cart\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
        }


    }


    /**
     * To delete Row Cart from the cart
     * @param object
     */
    @Override
    public void delete(Object object) throws SQLException {

        //RowCart rowCart = (RowCart) object;
        int rowNumber = (int) object;

        String query = "DELETE FROM cart_table WHERE `row_number`=?";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rowNumber);
            int result = preparedStatement.executeUpdate();

            if(result > 0){
                JOptionPane.showMessageDialog(null, "Row delete successfully\nCart updated\n");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
        }


    }


    /**
     * @param object
     */
    @Override
    public void update(Object object) throws SQLException {

        RowCart rowCart = (RowCart) object;
        String query = "UPDATE cart_table SET quantity=?, total_amount=? WHERE item_id=? AND `row_number`=?";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            //parameters to update
            preparedStatement.setInt(1, rowCart.getItemQuantity());
            preparedStatement.setDouble(2, rowCart.getTotalAmount());
            //check parameters
            preparedStatement.setInt(3, rowCart.getItemID());
            preparedStatement.setInt(4, rowCart.getRowNumber());

            int result = preparedStatement.executeUpdate();
            if(result > 0){

                JOptionPane.showMessageDialog(null, "CART ROW UPDATE SUCCESSFULLY!\n");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
        }

    }

    /**
     * @param object
     * @return
     */
    @Override
    public RowCart search(Object object) {

        int ticketID = (int) object;
        RowCart rowCart = new RowCart();
        String query = "SELECT * FROM cart_table WHERE `ticket_id`=?";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ticketID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                rowCart.setTicketID(resultSet.getInt("ticket_id"));
                rowCart.setItemID(resultSet.getInt("item_id"));
                rowCart.setItemName(resultSet.getString("item_name"));
                rowCart.setItemCategory(resultSet.getString("item_category"));
                rowCart.setItemQuantity(resultSet.getInt("quantity"));
                rowCart.setItemPrice(resultSet.getDouble("unit_price"));
                rowCart.setTotalAmount(resultSet.getDouble("total_amount"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return rowCart;

    }

    /**
     * @return
     */


    @Override
    public List<RowCart> list() throws SQLException {

        List<RowCart> cart = new ArrayList<>();
        String query = "SELECT * FROM cart_table";


        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){

                JOptionPane.showMessageDialog(null, "Your Cart is Empty!\n");

            }

            while(resultSet.next()){

                RowCart rowCart = new RowCart();
                rowCart.setRowNumber((resultSet.getInt("row_number")));
                rowCart.setTicketID(resultSet.getInt("ticket_id"));
                rowCart.setItemID(resultSet.getInt("item_id"));
                rowCart.setItemName(resultSet.getString("item_name"));
                rowCart.setItemCategory(resultSet.getString("item_category"));
                rowCart.setItemQuantity(resultSet.getInt("quantity"));
                rowCart.setItemPrice(resultSet.getDouble("unit_price"));
                rowCart.setTotalAmount(resultSet.getDouble("total_amount"));
                cart.add(rowCart);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }

        return cart;
    }
}
