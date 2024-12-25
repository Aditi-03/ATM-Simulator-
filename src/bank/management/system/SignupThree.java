package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {
    JRadioButton type1, type2, type3, type4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;
    String formno;
    ButtonGroup typeGroup;

    public SignupThree(String formno) {
        this.formno = formno;
        setLayout(null);

        JLabel accountDetails = new JLabel("Page3: Account Details");
        accountDetails.setFont(new Font("RaleWay", Font.BOLD, 22));
        accountDetails.setBounds(290, 80, 400, 30);
        add(accountDetails);

        JLabel acctype = new JLabel("Account Type");
        acctype.setFont(new Font("RaleWay", Font.BOLD, 20));
        acctype.setBounds(100, 140, 400, 30);
        add(acctype);

        type1 = new JRadioButton("Saving Account");
        type1.setBounds(100, 190, 200, 30);
        add(type1);

        type2 = new JRadioButton(" Fixed Deposit Account");
        type2.setBounds(360, 190, 200, 30);
        add(type2);

        type3 = new JRadioButton("Current Account");
        type3.setBounds(100, 230, 200, 30);
        add(type3);

        type4 = new JRadioButton("Recurring Account");
        type4.setBounds(360, 230, 200, 30);
        add(type4);

        typeGroup = new ButtonGroup();

        typeGroup.add(type1);
        typeGroup.add(type2);
        typeGroup.add(type3);
        typeGroup.add(type4);

        JLabel cardno = new JLabel("Card Number");
        cardno.setFont(new Font("RaleWay", Font.BOLD, 20));
        cardno.setBounds(100, 280, 400, 30);
        add(cardno);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-4184");
        number.setFont(new Font("RaleWay", Font.BOLD, 18));
        number.setBounds(340, 280, 400, 30);
        add(number);

        JLabel cardtext = new JLabel("Your 16 Digit Card Number");
        cardtext.setFont(new Font("RaleWay", Font.PLAIN, 12));
        cardtext.setBounds(100, 310, 400, 30);
        add(cardtext);

        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("RaleWay", Font.BOLD, 20));
        pin.setBounds(100, 350, 400, 30);
        add(pin);

        JLabel pinno = new JLabel("XXXX");
        pinno.setFont(new Font("RaleWay", Font.BOLD, 18));
        pinno.setBounds(340, 350, 400, 30);
        add(pinno);

        JLabel pintext = new JLabel("Your 4 Digit Pin");
        pintext.setFont(new Font("RaleWay", Font.PLAIN, 12));
        pintext.setBounds(100, 380, 400, 30);
        add(pintext);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("RaleWay", Font.BOLD, 20));
        services.setBounds(100, 420, 400, 30);
        add(services);

        c1 = new JCheckBox("ATM Card");
        c1.setFont(new Font("RaleWay", Font.PLAIN, 12));
        c1.setBounds(100, 470, 200, 30);
        add(c1);

        c2 = new JCheckBox("Internet Banking");
        c2.setFont(new Font("RaleWay", Font.PLAIN, 12));
        c2.setBounds(360, 470, 200, 30);
        add(c2);

        c3 = new JCheckBox("Mobile Banking");
        c3.setFont(new Font("RaleWay", Font.PLAIN, 12));
        c3.setBounds(100, 520, 200, 30);
        add(c3);

        c4 = new JCheckBox("Emails & SMS Alerts");
        c4.setFont(new Font("RaleWay", Font.PLAIN, 12));
        c4.setBounds(360, 520, 200, 30);
        add(c4);

        c5 = new JCheckBox("Check Book");
        c5.setFont(new Font("RaleWay", Font.PLAIN, 12));
        c5.setBounds(100, 570, 200, 30);
        add(c5);

        c6 = new JCheckBox("E-Statement");
        c6.setFont(new Font("RaleWay", Font.PLAIN, 12));
        c6.setBounds(360, 570, 200, 30);
        add(c6);

        c7 = new JCheckBox("I hereby decalre that the above entered details are correct to the best of my knowledge");
        c7.setFont(new Font("RaleWay", Font.PLAIN, 12));
        c7.setBounds(100, 630, 800, 30);
        add(c7);

        submit = new JButton("Submit");
        submit.setFont(new Font("RaleWay", Font.BOLD, 18));
        submit.setBounds(300, 700, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setFont(new Font("RaleWay", Font.BOLD, 18));
        cancel.setBounds(440, 700, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {

            String type = null;

            if (type1.isSelected()) {
                type = "Saving";
            } else if (type2.isSelected()) {
                type = "Fixed Deposit";
            } else if (type3.isSelected()) {
                type = "Current";
            } else if (type3.isSelected()) {
                type = "Recurring Deposit";
            }

            Random random = new Random();
            String cardno = "" + Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
            String pinno = "" + Math.abs((random.nextLong() % 9000L) + 1000L);

            String service = "";
            if (c1.isSelected()) {
                service = service + " ATM Card";
            } else if (c2.isSelected()) {
                service = service + " Internet Banking";
            } else if (c3.isSelected()) {
                service = service + " Mobile Banking";
            } else if (c4.isSelected()) {
                service = service + " Email & SMS Alerts";
            } else if (c5.isSelected()) {
                service = service + " Check Book";
            } else if (c6.isSelected()) {
                service = service + " E-Statement";
            }

            try {
                if (type.equals("")) {
                    JOptionPane.showMessageDialog(null, "Account Type is Required");
                } else {
                    Conn c = new Conn();
                    String query = "insert into signupthree values('" + formno + "', '" + type + "', '" + cardno
                            + "', '" + pinno + "', '" + service + "');";
                    String query2 = "insert into login values('" + formno + "', '" + cardno + "', '" + pinno + "');";

                    c.s.executeUpdate(query);
                    c.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null, "Card Number: " + cardno + "\n Pin: " + pinno);
                }

            } catch (

            Exception ex) {
                System.out.println(ex);
            }

        }

        else if (e.getSource() == cancel) {
    
            c1.setSelected(false);
            c2.setSelected(false);
            c3.setSelected(false);
            c4.setSelected(false);
            c5.setSelected(false);
            c6.setSelected(false);
            c7.setSelected(false);

        }

    }

    public static void main(String[] args) {
        new SignupThree("");
    }
}
