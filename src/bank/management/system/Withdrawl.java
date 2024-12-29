package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {

    String pinnumber;
    JLabel text;
    JTextField amount;
    JButton withdraw, back;

    Withdrawl(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(180, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("RaleWay", Font.BOLD, 20));
        amount.setBounds(180, 350, 320, 30);
        image.add(amount);

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 485, 150, 30);
        withdraw.addActionListener(this);
        image.add(withdraw);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        getContentPane().setBackground(Color.WHITE);
        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdraw) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount");
            } else {
                try {
                    Conn c = new Conn();
                    String query1 = "SELECT balance FROM bank WHERE pin = '" + pinnumber
                            + "' ORDER BY date DESC LIMIT 1";
                    ResultSet rs = c.s.executeQuery(query1);

                    int currentBalance = 0;
                    if (rs.next()) {
                        currentBalance = rs.getInt("balance");
                    }

                    int withdrawAmount = Integer.parseInt(number);
                    int newBalance = currentBalance - withdrawAmount;

                    if (newBalance < 0) {
                        JOptionPane.showMessageDialog(null, "Insufficient balance");
                        return;
                    }
                    String query = "insert into bank values('" + pinnumber + "', '" + date + "','Withdrawl', '" + number
                            + "', '" + newBalance + "');";

                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Rs " + number + " withdrawl successfully");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);

                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }

        } else if (e.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}
