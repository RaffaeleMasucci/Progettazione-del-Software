package Controller.Handler;

import Model.DAOFactoryMethod.CRUD;
import Model.TransferObject.RowCart;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class ComputeAmountHandler {

    public static void computeAmount(JTextField subtotalTextField, JTextField taxTextField,
                                     JTextField totalTextField, JTextField pointsTextField,
                                     CRUD rowCartDAO, int ticketID){

        System.out.println("COMPUTE AMOUNT HANDLER\n");
        List<RowCart> cart = null;
        try {
            cart = (List<RowCart>) rowCartDAO.list();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        double subtotal = 0;

        for(RowCart rowCart : cart){
            if(rowCart.getTicketID() == ticketID) {
                subtotal = subtotal + rowCart.getTotalAmount();
                if(subtotal == 0){

                    JOptionPane.showMessageDialog(null, "Your Cart is Empty!\n");

                }
            }

        }

        subtotalTextField.setText(String.valueOf((double) Math.round(subtotal * 100)/100));

        double tax = subtotal * 0.22;
        taxTextField.setText(String.valueOf((double) Math.round(tax * 100)/100));


        double total = tax + subtotal;
        totalTextField.setText(String.valueOf((double) Math.round(total * 100)/100));


        int loyaltyPoints = (int) total / 5;
        pointsTextField.setText(String.valueOf(loyaltyPoints));

    }
}
