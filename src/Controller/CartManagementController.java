package Controller;

import Controller.Handler.ShowCartHandler;
import Controller.Handler.ShowItemHandler;
import Model.DAOFactoryMethod.CRUD;
import Model.TransferObject.Item;
import Model.TransferObject.RowCart;
import View.DashboardView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

public class CartManagementController implements MouseListener{

    boolean found = false;
    DashboardView dashboardView;

    CRUD itemDAO, rowCartDAO;

    Item item;



    //Text Field--------------------------------------------------------------------------------------------------------
    JTextField textFieldTicketID;
    //Text Field--------------------------------------------------------------------------------------------------------

    //Spinner-----------------------------------------------------------------------------------------------------------
    JSpinner quantitySpinner, quantityUpdateSpinner;
    //Spinner-----------------------------------------------------------------------------------------------------------


    //Button------------------------------------------------------------------------------------------------------------
    JButton addButton, deleteButton, showButton;
    //Button------------------------------------------------------------------------------------------------------------


    //Item Stock parameters---------------------------------------------------------------------------------------------
    int selectedRow, itemID, itemStock;

    String itemName, itemCategory;

    Double itemPrice;
    //Item parameters---------------------------------------------------------------------------------------------------


    //RowCart parameters------------------------------------------------------------------------------------------------
    int rowNumber, itemSelectedQuantity;
    Double amount;
    //RowCart parameters------------------------------------------------------------------------------------------------


    //TicketID----------------------------------------------------------------------------------------------------------
    int ticketID;
    //TicketID----------------------------------------------------------------------------------------------------------


    //DefaultTableModel-------------------------------------------------------------------------------------------------
    DefaultTableModel defaultTableModelItem, defaultTableModelCart;
    //DefaultTableModel-------------------------------------------------------------------------------------------------


    //Table-------------------------------------------------------------------------------------------------------------
    JTable itemTable, cartTable;
    //Table-------------------------------------------------------------------------------------------------------------

    public CartManagementController(DashboardView dashboardView, CRUD itemDAO, CRUD rowCartDAO) {
        this.dashboardView = dashboardView;
        this.itemDAO = itemDAO;
        this.rowCartDAO = rowCartDAO;



            do{

                textFieldTicketID = this.dashboardView.getTextFieldTicketID();

            }while (textFieldTicketID.getText().isEmpty());



        System.out.println("TEXT FIELD TICKET_ID IS NOT EMPTY!\n");
        System.out.println(textFieldTicketID.getText());
        System.out.println("CART CONTROLLER STARTED\n");

        ticketID = Integer.parseInt(textFieldTicketID.getText());
        System.out.println("TICKET_ID: "+ticketID+"\n");

        addButton = this.dashboardView.getAddToCartButton();
        deleteButton = this.dashboardView.getDeleteRowCartButton();
        showButton = this.dashboardView.getShowDetailsButton();
        quantitySpinner = this.dashboardView.getSpinnerQuantity();
        quantityUpdateSpinner = this.dashboardView.getSpinnerQuantityUpdate();

        itemTable = this.dashboardView.getTableItem();
        cartTable = this.dashboardView.getTableCart();

        itemTable.addMouseListener(this);
        cartTable.addMouseListener(this);

        defaultTableModelItem = this.dashboardView.getDefaultTableModelItem();
        defaultTableModelCart = this.dashboardView.getDefaultTableModelCart();

        ShowItemHandler.showItem(defaultTableModelItem, itemDAO);
        ShowCartHandler.showCart(ticketID, defaultTableModelCart,rowCartDAO);


//add row cart----------------------------------------------------------------------------------------------------------
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //read item parameter from row selected
                selectedRow = itemTable.getSelectedRow();
                itemID = (int) defaultTableModelItem.getValueAt(selectedRow, 0);
                itemName = (String) defaultTableModelItem.getValueAt(selectedRow, 1);
                itemCategory = (String) defaultTableModelItem.getValueAt(selectedRow, 2);
                itemStock = (int) defaultTableModelItem.getValueAt(selectedRow, 3);
                itemPrice = (double) defaultTableModelItem.getValueAt(selectedRow, 4);

                if ((int) quantitySpinner.getValue() == 0) {

                    JOptionPane.showMessageDialog(null, "Missing information!\nSelect a quantity greater than 0\n");
                } else if ((int) quantitySpinner.getValue() > itemStock || itemStock == 0) {

                    JOptionPane.showMessageDialog(null, "Not enough stock!\n Or selected quantity is not available\n");

                } else {

                    itemSelectedQuantity = (int) quantitySpinner.getValue();
                    amount = (double) itemSelectedQuantity * itemPrice;

                    RowCart rowCart = new RowCart();
                    rowCart.setTicketID(ticketID);
                    rowCart.setItemID(itemID);
                    rowCart.setItemName(itemName);
                    rowCart.setItemCategory(itemCategory);
                    rowCart.setItemQuantity(itemSelectedQuantity);
                    rowCart.setItemPrice(itemPrice);
                    rowCart.setTotalAmount(amount);

                    //before entering, check that the selected item is not already in the shopping cart
                    try {
                        List<RowCart> cart = (List<RowCart>) rowCartDAO.list();
                        if(cart.isEmpty()){
                            //insert new Row in the Cart
                            rowCartDAO.insert(rowCart);
                        }
                        else{
                            for(RowCart rowCartCheck : cart){
                                if(rowCartCheck.getTicketID() == ticketID && rowCartCheck.getItemID() == itemID){

                                    found = true;
                                    rowCart.setRowNumber(rowCartCheck.getRowNumber());
                                    rowCart.setItemQuantity(itemSelectedQuantity + rowCartCheck.getItemQuantity());
                                    rowCart.setTotalAmount(amount + rowCartCheck.getTotalAmount());
                                    rowCartDAO.update(rowCart);


                                }
                            }

                            if(!found){
                                //insert new Row in the Cart
                                rowCartDAO.insert(rowCart);

                            }

                        }


                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }finally {
                        ShowCartHandler.showCart(ticketID, defaultTableModelCart,rowCartDAO);

                    }



                        //update items table
                        try {

                            item = (Item) itemDAO.search(itemID);
                            itemStock = item.getQuantity();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        itemStock = itemStock - itemSelectedQuantity;//new stock quantity
                        item.setId(itemID);
                        item.setQuantity(itemStock);

                        try {

                            itemDAO.update(item);

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }finally {
                            ShowItemHandler.showItem(defaultTableModelItem,itemDAO);
                        }
                        JOptionPane.showMessageDialog(null, "Row Cart added!\n");
                        quantitySpinner.setValue(0);
                        quantitySpinner.setEnabled(false);



                }


            }
        });
//add row cart-------------------------------------------------------------------------------------------------------

//delete row cart-------------------------------------------------------------------------------------------------------
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //read selected cart row's parameters from cart table
                selectedRow = cartTable.getSelectedRow();
                rowNumber = (int) cartTable.getValueAt(selectedRow, 0);//row number
                itemID = (int) cartTable.getValueAt(selectedRow, 1);//id in row cart
                itemSelectedQuantity = (int) cartTable.getValueAt(selectedRow, 4);//quantity in row cart


                //update item table
                try {
                    item = (Item) itemDAO.search(itemID);
                    itemStock = item.getQuantity() + itemSelectedQuantity;
                    item.setId(itemID);
                    item.setQuantity(itemStock);

                    itemDAO.update(item);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    ShowItemHandler.showItem(defaultTableModelItem,itemDAO);
                    //delete selected cart row
                    try {

                        rowCartDAO.delete(rowNumber);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }finally {
                        deleteButton.setEnabled(false);
                        ShowCartHandler.showCart(ticketID, defaultTableModelCart, rowCartDAO);

                    }
                }
            }
        });

//delete row cart-------------------------------------------------------------------------------------------------------


//update row cart quantity----------------------------------------------------------------------------------------------




//update row cart quantity----------------------------------------------------------------------------------------------

 }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

        //when user clicks on item table row, the quantity spinner and the add button are enabled
        if(e.getSource() == this.dashboardView.getTableItem()){

            quantitySpinner.setEnabled(true);
            quantityUpdateSpinner.setEnabled(false);
            addButton.setEnabled(true);
            deleteButton.setEnabled(false);


        }

        //when user clicks on a cart table row, the delete button is enabled
        if(e.getSource() == cartTable){

            addButton.setEnabled(false);
            quantityUpdateSpinner.setEnabled(true);
            deleteButton.setEnabled(true);

        }

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {


    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }


}
