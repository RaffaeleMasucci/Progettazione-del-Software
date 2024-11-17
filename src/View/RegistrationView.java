package View;

import javax.swing.*;

public class RegistrationView extends JFrame {

    private JTextField textFieldTicketRegistration;
    private JButton buttonConfirm;
    private JPanel rootPanel;

    public RegistrationView(){
        super("Registration View");

        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(700, 700);

        setVisible(true);

    }

    /**
     * Return to the Controller the reference of the ticket's id text field
     * @return textFieldTicketId
     */
    public JTextField getTextFieldTicketId() {
        return textFieldTicketRegistration;
    }

    /**
     * Return to the Controller the reference of the ticket validation button
     * to assign it an event handler
     * @return buttonVerifyTicket
     */
    public JButton getButtonConfirm() {
        return buttonConfirm;
    }


}
