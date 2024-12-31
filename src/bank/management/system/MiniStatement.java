package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    String pinnumber;

    MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        JLabel mini = new JLabel();
        add(mini);

        JLabel bank = new JLabel("XYZ Bank");
        bank.setFont(new Font("RaleWay", Font.BOLD, 18));
        bank.setBounds(150, 20, 200, 30);
        add(bank);

        JLabel card = new JLabel();
        card.setFont(new Font("RaleWay", Font.PLAIN, 14));
        card.setBounds(20, 60, 300, 20);
        add(card);

        JLabel balance = new JLabel();
        balance.setFont(new Font("RaleWay", Font.PLAIN, 14));
        balance.setBounds(20, 400, 300, 20);
        add(balance);

        try {
            Conn c = new Conn();
            // Fetch card number
            String query = "SELECT * FROM login WHERE pin = '" + pinnumber + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX"
                        + rs.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            Conn c = new Conn();

            // Fetch all transactions
            String query = "SELECT * FROM bank WHERE pin = '" + pinnumber + "' ORDER BY date DESC LIMIT 10";

            ResultSet rs = c.s.executeQuery(query);
            StringBuilder transactions = new StringBuilder("<html>");
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                int amount = rs.getInt("amount");

                transactions.append(date).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(type).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append("Rs ").append(amount).append("<br>");
            }
            transactions.append("</html>");
            mini.setText(transactions.toString());

            // Fetch the most recent balance
            String balanceQuery = "SELECT balance FROM bank WHERE pin = '" + pinnumber + "' ORDER BY date DESC LIMIT 1";
            ResultSet balanceRs = c.s.executeQuery(balanceQuery);
            if (balanceRs.next()) {
                int bal = balanceRs.getInt("balance");
                balance.setText("Your Current account balance is Rs " + bal);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        mini.setBounds(20, 120, 400, 250);

        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("");
    }
}
