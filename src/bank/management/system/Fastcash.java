package bank.management.system;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Fastcash extends JFrame implements ActionListener {

    JButton deposit, withdrawl, fastcash, statement, pinchange, enquiry, exit;
    String pinnumber;

    Fastcash(String pinnumber) {
        pinnumber = this.pinnumber;
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

        deposit = new JButton("RS 100");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("RS 200");
        withdrawl.setBounds(350, 415, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("RS 500");
        fastcash.setBounds(170, 450, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        statement = new JButton("RS 1000");
        statement.setBounds(350, 450, 150, 30);
        statement.addActionListener(this);
        image.add(statement);

        pinchange = new JButton("RS 5000");
        pinchange.setBounds(170, 485, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        enquiry = new JButton("RS 10000");
        enquiry.setBounds(350, 485, 150, 30);
        enquiry.addActionListener(this);
        image.add(enquiry);

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
            Conn c = new Conn();
            try {
                String query = "Select * from bank where pin='" + pinnumber + "' ";
                ResultSet rs = c.s.executeQuery(query);

                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
                if (e.getSource() != exit && balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance!");
                    return;
                }
                Date date = new Date();
                String query1 = "insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawl', '" + amount
                        + "')";
                c.s.executeUpdate(query1);
                JOptionPane.showMessageDialog(null, "Rs " + amount + "debited successfully");
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
