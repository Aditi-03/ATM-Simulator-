package bank.management.system;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton hundred, twohundred, fivehundred, thousand, fivethousand, tenthousand, exit;
    String pinnumber;
    int balance = 0;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        exit = new JButton("Back");
        exit.setBounds(350, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        Conn c = new Conn();
        try {
            String query = "Select * from bank where pin='" + pinnumber + "' ";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        JLabel text = new JLabel("Your current account balance is Rs " + balance);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        getContentPane().setBackground(Color.WHITE);
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);

        // if (e.getSource() == exit) {
        // setVisible(false);
        // new Transactions(pinnumber).setVisible(true);
        // } else {
        // String amount = ((JButton) e.getSource()).getText().substring(3);
        // Conn c = new Conn();
        // try {
        // String query = "Select * from bank where pin='" + pinnumber + "' ";
        // ResultSet rs = c.s.executeQuery(query);

        // int balance = 0;
        // while (rs.next()) {
        // if (rs.getString("type").equals("Deposit")) {
        // balance += Integer.parseInt(rs.getString("amount"));
        // } else {
        // balance -= Integer.parseInt(rs.getString("amount"));
        // }
        // }
        // if (e.getSource() != exit && balance < Integer.parseInt(amount)) {
        // JOptionPane.showMessageDialog(null, "Insufficient Balance!");
        // return;
        // }
        // Date date = new Date();
        // String query1 = "insert into bank values('" + pinnumber + "', '" + date + "',
        // 'Withdrawl', '" + amount
        // + "')";
        // c.s.executeUpdate(query1);
        // JOptionPane.showMessageDialog(null, "Rs " + amount + "debited successfully");
        // setVisible(false);
        // new Transactions(pinnumber).setVisible(true);

        // } catch (Exception ex) {
        // System.out.println(ex);
        // }
    }

    public static void main() {
        new BalanceEnquiry("");
    }

}
