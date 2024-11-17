package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class DashboardView extends JFrame {

    private JPanel rootPanel;
    private JTable tableItem, tableCart;
    private JComboBox comboBoxCategory;
    private JSpinner spinnerQuantity;
    private JButton addToCartButton;
    private JTextArea textArea;
    private JButton deleteRowCartButton;
    private JButton printButton;
    private JButton exitButton;

    private JTextField subtotalTextField;
    private JTextField taxTextField;
    private JTextField totalTextField;
    private JTextField loyaltyTextField;
    private JTextField pointsTextField;
    private JComboBox comboBoxPaymentMethod;
    private JTextField textFieldTicketID;
    private JButton showDetailsButton;
    private JSpinner spinnerQuantityUpdate;

    private DefaultTableModel defaultTableModelItem, defaultTableModelCart;




    public DashboardView(){
        super("Dashboard Test");

        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,900);





        //set Items Table
        JTableHeader tableHeaderItem = tableItem.getTableHeader();
        tableHeaderItem.setBackground(Color.ORANGE);
        tableHeaderItem.setForeground(Color.BLACK);
        tableHeaderItem.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));

        String [] columnsItems = {"ID", "Item Name", "Category", "Quantity", "Price"};
        defaultTableModelItem = (DefaultTableModel) tableItem.getModel();
        defaultTableModelItem.setColumnIdentifiers(columnsItems);



        //set Cart Table
        JTableHeader tableHeaderCart = tableCart.getTableHeader();
        tableHeaderCart.setBackground(Color.ORANGE);
        tableHeaderCart.setForeground(Color.BLACK);
        tableHeaderCart.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));

        String [] columnsCart = {"Row#", "Item ID", "Item Name", "Category", "Quantity", "Price", "Total"};
        defaultTableModelCart = (DefaultTableModel) tableCart.getModel();
        defaultTableModelCart.setColumnIdentifiers(columnsCart);



        setVisible(false);

    }


    public JTable getTableItem() {
        return tableItem;
    }

    public void setTableItem(JTable tableItem) {
        this.tableItem = tableItem;
    }

    public JTable getTableCart() {
        return tableCart;
    }

    public void setTableCart(JTable tableCart) {
        this.tableCart = tableCart;
    }

    public JComboBox getComboBoxCategory() {
        return comboBoxCategory;
    }

    public void setComboBoxCategory(JComboBox comboBoxCategory) {
        this.comboBoxCategory = comboBoxCategory;
    }

    public JSpinner getSpinnerQuantity() {
        return spinnerQuantity;
    }

    public void setSpinnerQuantity(JSpinner spinnerQuantity) {
        this.spinnerQuantity = spinnerQuantity;
    }

    public JButton getAddToCartButton() {
        return addToCartButton;
    }

    public void setAddToCartButton(JButton addToCartButton) {
        this.addToCartButton = addToCartButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public DefaultTableModel getDefaultTableModelItem() {
        return defaultTableModelItem;
    }

    public void setDefaultTableModelItem(DefaultTableModel defaultTableModelItem) {
        this.defaultTableModelItem = defaultTableModelItem;
    }

    public DefaultTableModel getDefaultTableModelCart() {
        return defaultTableModelCart;
    }

    public void setDefaultTableModelCart(DefaultTableModel defaultTableModelCart) {
        this.defaultTableModelCart = defaultTableModelCart;
    }

    public JButton getDeleteRowCartButton() {
        return deleteRowCartButton;
    }

    public void setDeleteRowCartButton(JButton deleteRowCartButton) {
        this.deleteRowCartButton = deleteRowCartButton;
    }

    public JButton getPrintButton() {
        return printButton;
    }

    public void setPrintButton(JButton printButton) {
        this.printButton = printButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JTextField getSubtotalTextField() {
        return subtotalTextField;
    }

    public void setSubtotalTextField(JTextField subtotalTextField) {
        this.subtotalTextField = subtotalTextField;
    }

    public JTextField getTaxTextField() {
        return taxTextField;
    }

    public void setTaxTextField(JTextField taxTextField) {
        this.taxTextField = taxTextField;
    }

    public JTextField getTotalTextField() {
        return totalTextField;
    }

    public void setTotalTextField(JTextField totalTextField) {
        this.totalTextField = totalTextField;
    }

    public JTextField getLoyaltyTextField() {
        return loyaltyTextField;
    }

    public void setLoyaltyTextField(JTextField loyaltyTextField) {
        this.loyaltyTextField = loyaltyTextField;
    }

    public JTextField getPointsTextField() {
        return pointsTextField;
    }

    public void setPointsTextField(JTextField pointsTextField) {
        this.pointsTextField = pointsTextField;
    }

    public JComboBox getComboBoxPaymentMethod() {
        return comboBoxPaymentMethod;
    }

    public void setComboBoxPaymentMethod(JComboBox comboBoxPaymentMethod) {
        this.comboBoxPaymentMethod = comboBoxPaymentMethod;
    }

    public JTextField getTextFieldTicketID() {
        return textFieldTicketID;
    }

    public void setTextFieldTicketID(JTextField textFieldTicketID) {
        this.textFieldTicketID = textFieldTicketID;
    }

    public JButton getShowDetailsButton() {
        return showDetailsButton;
    }

    public void setShowDetailsButton(JButton showDetailsButton) {
        this.showDetailsButton = showDetailsButton;
    }

    public JSpinner getSpinnerQuantityUpdate() {
        return spinnerQuantityUpdate;
    }

    public void setSpinnerQuantityUpdate(JSpinner spinnerQuantityUpdate) {
        this.spinnerQuantityUpdate = spinnerQuantityUpdate;
    }
}
