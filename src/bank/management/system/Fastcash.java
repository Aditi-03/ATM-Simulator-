package bank.management.system;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Fastcash extends JFrame implements ActionListener {

    JButton hundred, twohundred, fivehundred, thousand, fivethousand, tenthousand, exit;
    String pinnumber;

    Fastcash(String pinnumber) {
        this.pinnumber = pinnumber;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Select withdrawl amount");
        text.setBounds(220, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        hundred = new JButton("RS 100");
        hundred.setBounds(170, 415, 150, 30);
        hundred.addActionListener(this);
        image.add(hundred);

        twohundred = new JButton("RS 200");
        twohundred.setBounds(350, 415, 150, 30);
        twohundred.addActionListener(this);
        image.add(twohundred);

        fivehundred = new JButton("RS 500");
        fivehundred.setBounds(170, 450, 150, 30);
        fivehundred.addActionListener(this);
        image.add(fivehundred);

        thousand = new JButton("RS 1000");
        thousand.setBounds(350, 450, 150, 30);
        thousand.addActionListener(this);
        image.add(thousand);

        fivethousand = new JButton("RS 5000");
        fivethousand.setBounds(170, 485, 150, 30);
        fivethousand.addActionListener(this);
        image.add(fivethousand);

        tenthousand = new JButton("RS 10000");
        tenthousand.setBounds(350, 485, 150, 30);
        tenthousand.addActionListener(this);
        image.add(tenthousand);

        exit = new JButton("Back");
        exit.setBounds(350, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        getContentPane().setBackground(Color.WHITE);
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            String amount = ((JButton) e.getSource()).getText().substring(3);
            Date date = new Date();
            Conn c = new Conn();
            try {
                
                    String query1 = "SELECT balance FROM bank WHERE pin = '" + pinnumber
                            + "' ORDER BY date DESC LIMIT 1";
                    ResultSet rs = c.s.executeQuery(query1);

                    int currentBalance = 0;
                    if (rs.next()) {
                        currentBalance = rs.getInt("balance");
                    }

                    int withdrawAmount = Integer.parseInt(amount);
                    int newBalance = currentBalance - withdrawAmount;

                    if (newBalance < 0) {
                        JOptionPane.showMessageDialog(null, "Insufficient balance");
                        return;
                    }
                    String query = "insert into bank values('" + pinnumber + "', '" + date + "','Withdrawl', '" + amount
                            + "', '" + newBalance + "');";

                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " + amount + " withdrawl successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public static void main() {
        new Fastcash("");
    }

}
