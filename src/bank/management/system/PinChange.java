package bank.management.system;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PinChange extends JFrame implements ActionListener {
    String pinnumber;

    JPasswordField pin, repin;
    JButton change, back;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        System.out.println(pinnumber);

        setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Change your PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 300, 400, 20);
        image.add(text);

        JLabel pintext = new JLabel("New PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 350, 180, 20);
        image.add(pintext);

        pin = new JPasswordField();
        pin.setFont(new Font("Raleway", Font.BOLD, 16));
        pin.setBounds(330, 350, 180, 20);
        image.add(pin);

        JLabel repintext = new JLabel(" Re-Enter New PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(160, 380, 180, 20);
        image.add(repintext);

        repin = new JPasswordField();
        repin.setFont(new Font("Raleway", Font.BOLD, 16));
        repin.setBounds(330, 380, 180, 20);
        image.add(repin);

        change = new JButton("Change");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {

            try {
                String npin = pin.getText();
                String nrepin = repin.getText();

                if (!npin.equals(nrepin)) {
                    JOptionPane.showMessageDialog(null, "Entered pin does not match");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter new PIN");
                    return;
                }
                if (nrepin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please re-enter new PIN");
                    return;
                }

                Conn c = new Conn();
                String query1 = "update bank set pin ='" + npin + "' where pin='" + pinnumber + "'";
                String query2 = "update login set pin ='" + npin + "' where pin='" + pinnumber + "'";
                String query3 = "update signupthree set pin ='" + npin + "' where pin='" + pinnumber + "'";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);
                JOptionPane.showMessageDialog(null, "PIN changed successfully");
                setVisible(false);
                new Transactions(npin).setVisible(true);

            } catch (Exception ex) {
                System.out.println(ex);
            }

            // }

        } else if (e.getSource() == back)

        {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        }
    }

    public static void main(String[] args) {
        new PinChange("");
    }
}
