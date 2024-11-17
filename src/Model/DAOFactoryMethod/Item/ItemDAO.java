package Model.DAOFactoryMethod.Item;

import Model.DAOFactoryMethod.CRUD;
import Model.DBConnection;
import Model.TransferObject.Item;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements CRUD {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

/*
    public Item searchItem(int id) throws SQLException {


    }
*/

    @Override
    public void insert(Object object) throws SQLException {

        //never used
    }

    @Override
    public void delete(Object object) throws SQLException {

        //never used
    }

    //update item's quantity when it is added into the cart or removed from the cart
    @Override
    public void update(Object object) throws SQLException {

        Item item = (Item) object;

        String query = "UPDATE item_table SET quantity=? WHERE id=?";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, item.getQuantity());
            preparedStatement.setInt(2,item.getId());
            int result = preparedStatement.executeUpdate();

            if(result > 0){

                JOptionPane.showMessageDialog(null, "*---------- Items Table Updated ----------*\n");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
        }

    }

    //search item by ID
    @Override
    public Item search(Object object) throws SQLException {

        int itemId = (int) object;
        Item item = new Item();
        String query = "SELECT * FROM item_table WHERE id=?";


        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, itemId);

            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setCategory(resultSet.getString("category"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice((int) resultSet.getDouble("price"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }

        return  item;

    }


    //show available items into their table
    @Override
    public List<Item> list() throws SQLException {

        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM item_table ";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {

                System.out.println("*---------- Items Table is Empty! ----------*\n");
                JOptionPane.showMessageDialog(null, "*---------- Items Table is Empty! ----------*\n");

            } else {

                readFromDB(items);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }

        return items;
    }

    //list items by category
    public ArrayList<Item> listByCategory(String category) throws SQLException {

        ArrayList<Item> items = new ArrayList<>();
        String query = "SELECT * FROM item_table WHERE category=?";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,category);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {

                System.out.println("*---------- Items Table is Empty for Selected Category! ----------*\n");
                JOptionPane.showMessageDialog(null, "*---------- Items Table is Empty for Selected Category! ----------*\n");

            }
            else{

                readFromDB(items);
            }


            } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }

        return items;
    }


    private void readFromDB(List<Item> items) throws SQLException {
        while (resultSet.next()){

            Item item = new Item();
            item.setId(resultSet.getInt("id"));
            item.setName(resultSet.getString("name"));
            item.setCategory(resultSet.getString("category"));
            item.setQuantity(resultSet.getInt("quantity"));
            item.setPrice(resultSet.getDouble("price"));
            items.add(item);

        }
    }

}
