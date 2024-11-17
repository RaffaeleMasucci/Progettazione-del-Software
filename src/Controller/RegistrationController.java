package Controller;

import Model.DAOFactoryMethod.CRUD;
import Model.TransferObject.Ticket;
import View.DashboardView;
import View.RegistrationView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class RegistrationController {

    RegistrationView registrationView;
    DashboardView dashboardView;
    CRUD ticketDAO;
    Ticket ticket;
    JTextField textFieldTicketRegistration;
    JButton confirmButton;



    public RegistrationController(RegistrationView registrationView, DashboardView dashboardView,CRUD ticketDAO){

        this.registrationView = registrationView;
        this.dashboardView = dashboardView;

        this.ticketDAO = ticketDAO;


        textFieldTicketRegistration = this.registrationView.getTextFieldTicketId();
        confirmButton = this.registrationView.getButtonConfirm();


        confirmButton.addActionListener(e -> {
            if(textFieldTicketRegistration.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Missing Information: insert a valid ticket#\n");
            }else{
                try {

                    ticket = (Ticket) ticketDAO.search(Integer.parseInt(textFieldTicketRegistration.getText()));
                    if(ticket.getTicketID() == Integer.parseInt(textFieldTicketRegistration.getText())){

                        //matching successfully
                        System.out.println("Registration Controller : ticket found\n"+ textFieldTicketRegistration.getText());
                        dashboardView.getTextFieldTicketID().setText(textFieldTicketRegistration.getText());
                        dashboardView.getTextFieldTicketID().setEnabled(true);

                        dashboardView.setVisible(true);
                        registrationView.dispose();


                    }


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }


        });

    }

}
