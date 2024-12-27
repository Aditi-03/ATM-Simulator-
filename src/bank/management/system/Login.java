package bank.management.system;

import java.awt.*;

import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton loginBtn, clear, signUp;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("ATM");
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 20, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("Card Number:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 160, 200, 40);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(350, 160, 220, 40);
        cardTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                // Check if the character is not a digit and block it
                if (!Character.isDigit(c)) {
                    e.consume(); // Ignore the event
                }
            }
        });

        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 240, 400, 40);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(350, 240, 220, 40);
        add(pinTextField);

        loginBtn = new JButton("SIGN IN");
        loginBtn.setBounds(350, 300, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);

        clear = new JButton("CLEAR");
        clear.setBounds(470, 300, 100, 30);
        clear.addActionListener(this);
        add(clear);

        signUp = new JButton("SIGNUP");
        signUp.setBounds(350, 350, 220, 30);
        signUp.addActionListener(this);
        add(signUp);

        getContentPane().setBackground(Color.WHITE);
        setSize(800, 480);
        setVisible(true);
        setLocation(350, 200);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");

        } else if (e.getSource() == loginBtn) {
            Conn c = new Conn();
            String cardNumber = cardTextField.getText();
            String pinnumber = pinTextField.getText();

            String query = "select * from login where cardnumber = '" + cardNumber + "' and pin = '" + pinnumber + "'";
            try {
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }

        } else if (e.getSource() == signUp) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }

    }

    public static void main(String[] args) {
        new Login();

    }

}
