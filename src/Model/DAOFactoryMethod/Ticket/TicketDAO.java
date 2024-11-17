package Model.DAOFactoryMethod.Ticket;

import Model.DAOFactoryMethod.CRUD;
import Model.DBConnection;
import Model.TransferObject.Ticket;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketDAO implements CRUD {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /**
     * @param object
     *
     */
    @Override
    public void insert(Object object) throws SQLException {

        Ticket ticket = (Ticket) object;
        String query = "INSERT INTO ticket_table(ticket_id) VALUES(?)";

        connection = DBConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, (ticket.getTicketNumber()*10));
        int result = preparedStatement.executeUpdate();
        if(result > 0){

            JOptionPane.showMessageDialog(null, "NEW TICKET ADDED TO ticket_table\n" +
                    "ticket_id = "+ticket.getTicketNumber()*10);
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
    public Ticket search(Object object) throws SQLException {

        int id = (int) object;
        Ticket ticket = new Ticket();
        String query = "SELECT * FROM ticket_table WHERE ticket_id=?";

       // String queryMax = "SELECT MAX(ticket_number) FROM (ticket_table)";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.isBeforeFirst()){
                JOptionPane.showMessageDialog(null, "Inserted Ticket is not present in the data base : it's expired!\n");
            }
            while (resultSet.next()){
                ticket.setTicketNumber(resultSet.getInt("ticket_number"));
                ticket.setTicketID(resultSet.getInt("ticket_id"));
                JOptionPane.showMessageDialog(null, "Inserted Ticker is present in the data base : it's valid!\n");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }


        return ticket;
    }


    /**
     * @return
     * @throws SQLException
     */
    @Override
    public List<?> list() throws SQLException {
        return null;
    }

    public Ticket searchMax(){

        Ticket ticket = new Ticket();
        String query = "SELECT MAX(ticket_number) FROM (ticket_table)";


        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                ticket.setTicketNumber(resultSet.getInt("ticket_number"));
                ticket.setTicketID(resultSet.getInt("ticket_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ticket;
    }
}


