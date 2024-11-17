package Model.DAOFactoryMethod.Bill;

import Model.TransferObject.Bill;
import Model.DAOFactoryMethod.CRUD;
import Model.DBConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDAO implements CRUD {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    /**
     * @param object
     */
    @Override
    public void insert(Object object) throws SQLException {

        Bill bill = (Bill) object;

        String query = "INSERT INTO bill_table(ticket_id, pay_method, amount, loyalty_card_id, loyalty_points) " +
                "VALUES(?, ?, ?, ?, ?)";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, bill.getTicketID());
            preparedStatement.setString(2, bill.getPayMethod());
            preparedStatement.setDouble(3, bill.getAmount());
            preparedStatement.setString(4, bill.getLoyaltyID());
            preparedStatement.setInt(5, bill.getLoyaltyPoints());
            int result = preparedStatement.executeUpdate();

            if(result > 0){
                JOptionPane.showMessageDialog(null, "Bill added successfully to table\n");
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
     * @throws SQLException
     */
    @Override
    public void delete(Object object) throws SQLException {

    }

    /**
     * @param object
     */
    @Override
    public void update(Object object) {

    }

    /**
     * @param object
     * @return
     */
    @Override
    public Object search(Object object) {
        return null;
    }

    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<?> list() throws SQLException {
        return null;
    }
}
