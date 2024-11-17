package Controller;

//payment & bill printing controller
//final step handler/controller

import Controller.Handler.ComputeAmountHandler;
import Model.TransferObject.Bill;
import Model.DAOFactoryMethod.CRUD;
import Model.TransferObject.RowCart;
import View.DashboardView;
import View.RegistrationView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.List;

public class PaymentController {

    DashboardView dashboardView;
    RegistrationView registrationView;


    CRUD billDAO, rowCartDAO, ticketDAO;

    Bill bill;


    //Button------------------------------------------------------------------------------------------------------------
    JButton showButton, printButton, exitButton;
    JTextArea textArea;
    //Button------------------------------------------------------------------------------------------------------------


    //ComboBox----------------------------------------------------------------------------------------------------------
    JComboBox paymentMethodComboBox;
    //ComboBox----------------------------------------------------------------------------------------------------------


    //Bill parameters---------------------------------------------------------------------------------------------------
    int ticketID, loyaltyPoints;
    String payMethod, loyaltyID;
    double total;
    //Bill parameters---------------------------------------------------------------------------------------------------


    //TextField---------------------------------------------------------------------------------------------------------

    JTextField ticketIdTextField, subtotalTextField, taxTextField,
            totalTextField, loyaltyTextField, pointsTextField;
    //TextField---------------------------------------------------------------------------------------------------------



    //DefaultTableModel-------------------------------------------------------------------------------------------------
    DefaultTableModel defaultTableModelCart;
    //DefaultTableModel-------------------------------------------------------------------------------------------------

    public PaymentController(DashboardView dashboardView, RegistrationView registrationView,
                             CRUD billDAO, CRUD rowCartDAO, CRUD ticketDAO, Bill bill){

        this.dashboardView = dashboardView;
        this.registrationView = registrationView;
        this.billDAO = billDAO;
        this.rowCartDAO = rowCartDAO;
        this.ticketDAO = ticketDAO;
        this.bill = bill;

       // do{
            showButton = this.dashboardView.getShowDetailsButton();
       // }while (!showButton.isEnabled());


        System.out.println("PAYMENT CONTROLLER STARTED\n");
        printButton = this.dashboardView.getPrintButton();

        defaultTableModelCart = this.dashboardView.getDefaultTableModelCart();

        paymentMethodComboBox = this.dashboardView.getComboBoxPaymentMethod();
        paymentMethodComboBox.setEnabled(true);
        loyaltyID = this.dashboardView.getLoyaltyTextField().getText();


        textArea = this.dashboardView.getTextArea();

        showButton = this.dashboardView.getShowDetailsButton();
        exitButton = this.dashboardView.getExitButton();

        ticketIdTextField = this.dashboardView.getTextFieldTicketID();
        subtotalTextField = this.dashboardView.getSubtotalTextField();
        taxTextField = this.dashboardView.getTaxTextField();
        totalTextField = this.dashboardView.getTotalTextField();
        loyaltyTextField = this.dashboardView.getLoyaltyTextField();
        pointsTextField = this.dashboardView.getPointsTextField();

        ticketID = Integer.parseInt(ticketIdTextField.getText());

        paymentMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("PAYMENT COMBO BOX CLICKED\n");
                payMethod = (String) paymentMethodComboBox.getSelectedItem();
                if(payMethod.equals("Select Payment")){

                    printButton.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Missing Information: select a valid payment method!\n");
                }
                else{
                    printButton.setEnabled(true);
                }

            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ComputeAmountHandler.computeAmount(subtotalTextField, taxTextField, totalTextField, pointsTextField,
                        rowCartDAO, ticketID);
                total = Double.parseDouble(totalTextField.getText());

                textArea.setText("      Spartan-Greek Museum\n");
                textArea.setText(textArea.getText()+ "      Circle 'The history is us'\n");
                textArea.setText(textArea.getText()+ "      589/King Road\n");
                textArea.setText(textArea.getText()+ "      +0161 128900328\n");
                textArea.setText(textArea.getText()+ "\t\tSale details\n" +
                        "---------------------------------------------------------------------\n");

                try {
                    List<RowCart> cart = (List<RowCart>) rowCartDAO.list();

                    for(RowCart rowCart : cart){
                        if(rowCart.getTicketID() == ticketID){
                            if(rowCart.getItemCategory().equals("ticket")){

                                //ticket found : insert it into ticket table
                                //search current ticket
                                /*
                                System.out.println("TICKET NUMBER:"+ticket.getTicketNumber() +
                                        "\nTICKET ID:"+ticket.getTicketID());


                                    ticketDAO.insert(ticket);
*/


                            }
                            textArea.setText(textArea.getText()+ "--> "+rowCart.getItemID() + "\t" + rowCart.getItemName() +
                            "\t" + rowCart.getItemQuantity() + "\t" + rowCart.getTotalAmount() +
                                    "\n---------------------------------------------------------------------\n");

                            //delete cart row
                            rowCartDAO.delete(rowCart.getRowNumber());
                        }
                    }

                    textArea.setText(textArea.getText()+ "-----------------------------------------------------\n");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }finally {
                    defaultTableModelCart.getDataVector().removeAllElements();
                    defaultTableModelCart.fireTableDataChanged();

                    showButton.setEnabled(false);
                    printButton.setEnabled(false);
                }


                //create bill and insert into bill table
                try {

                    bill.setTicketID(ticketID);
                    bill.setPayMethod(payMethod);
                    bill.setAmount(total);
                    bill.setLoyaltyID(loyaltyID);
                    bill.setLoyaltyPoints(loyaltyPoints);
                    billDAO.insert(bill);

                    textArea.setText(textArea.getText()+ "TicketID: " + ticketID + "\nPayMethod: " + payMethod + "\nLoyaltyID: " +
                            loyaltyID + "\nLoyaltyPoints: " + loyaltyPoints + "\nTotalAmount: " + total +
                            "\n***************************************************\n\t\tThank you and GoodBye!\n");


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }finally {

                    totalTextField.setText("");
                    taxTextField.setText("");
                    totalTextField.setText("");
                    loyaltyTextField.setText("");
                    printButton.setEnabled(false);
                    showButton.setEnabled(false);
                    paymentMethodComboBox.setSelectedItem("Select Payment");

                }

            }

            });


//show cart details-----------------------------------------------------------------------------------------------------
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ComputeAmountHandler.computeAmount(subtotalTextField, taxTextField, totalTextField,
                        pointsTextField, rowCartDAO, ticketID);

            }
        });
//show cart details-----------------------------------------------------------------------------------------------------

//exit button-----------------------------------------------------------------------------------------------------------
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                registrationView.getTextFieldTicketId().setText("");
                dashboardView.getTextFieldTicketID().setText("");
                dashboardView.setVisible(false);
                registrationView.setVisible(true);

            }
        });
//exit button-----------------------------------------------------------------------------------------------------------

    }

}

