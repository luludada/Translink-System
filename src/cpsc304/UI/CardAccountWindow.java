package cpsc304.UI;

import cpsc304.delegates.PassengerDelegate;
import cpsc304.model.entities.PassengerCard1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CardAccountWindow extends JFrame implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private PassengerDelegate delegate;
    private PassengerCard1 card;
    JTextField value;
    private JFrame frame;
    private JTable accountTable;

    public CardAccountWindow(String user_id){


        String[] columnNames = {"SIN", "Phone", "User ID", "Email", "PIN", "Name", "Card Num", "Balance"};
        accountTable = new JTable(new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        DefaultTableModel model = (DefaultTableModel) accountTable.getModel();




       //display account detail
       //Create a table with associated column using the get function

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));

        value = new JTextField(30);
        double amount = Double.parseDouble(value.getText());
        JButton button = new JButton("Add Money to Your Account");
        //button.addActionListener((e)->delegate.updateBalance(amount));
        buttonsPanel.add(button);
        value.setBounds(200,150, 200,30);
        add(value);
        buttonsPanel.add(new JLabel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
