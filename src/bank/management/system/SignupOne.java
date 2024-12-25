package bank.management.system;

import javax.swing.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
// import java.util.*;

public class SignupOne extends JFrame implements ActionListener

{
    long random;
    JTextField nameTextField, fnameTextField, genderTextField, emailTextField,
            addressTextField, cityTextField, stateTextField, pinTextField;
    JDateChooser dateChooser;
    JRadioButton male, female, married, unmarried, other;
    JButton nextBtn;

    SignupOne() {
        setLayout(null);
        random = (long) (Math.random() * 9000) + 1000;

        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("RaleWay", Font.BOLD, 36));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personalDetails = new JLabel("Page1: Personal Details");
        personalDetails.setFont(new Font("RaleWay", Font.BOLD, 22));
        personalDetails.setBounds(290, 80, 400, 30);
        add(personalDetails);

        JLabel name = new JLabel("Name:");
        name.setFont(new Font("RaleWay", Font.BOLD, 20));
        name.setBounds(100, 140, 400, 30);
        add(name);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("RaleWay", Font.BOLD, 16));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("RaleWay", Font.BOLD, 20));
        fname.setBounds(100, 190, 400, 30);
        add(fname);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("RaleWay", Font.BOLD, 16));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("RaleWay", Font.BOLD, 20));
        dob.setBounds(100, 240, 400, 30);
        add(dob);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 400, 30);
        add(dateChooser);

        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("RaleWay", Font.BOLD, 20));
        gender.setBounds(100, 290, 200, 30);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(300, 290, 100, 30);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 290, 100, 30);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel email = new JLabel("Email:");
        email.setFont(new Font("RaleWay", Font.BOLD, 20));
        email.setBounds(100, 340, 200, 30);
        add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("RaleWay", Font.BOLD, 16));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        JLabel mstatus = new JLabel("Martial Status:");
        mstatus.setFont(new Font("RaleWay", Font.BOLD, 20));
        mstatus.setBounds(100, 390, 200, 30);
        add(mstatus);

        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 390, 100, 30);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setBounds(600, 390, 100, 30);
        add(other);

        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(married);
        statusGroup.add(unmarried);
        statusGroup.add(other);

        JLabel address = new JLabel("Address:");
        address.setFont(new Font("RaleWay", Font.BOLD, 20));
        address.setBounds(100, 440, 200, 30);
        add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("RaleWay", Font.BOLD, 16));
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);

        JLabel city = new JLabel("City:");
        city.setFont(new Font("RaleWay", Font.BOLD, 20));
        city.setBounds(100, 490, 200, 30);
        add(city);

        cityTextField = new JTextField();
        cityTextField.setFont(new Font("RaleWay", Font.BOLD, 16));
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);

        JLabel state = new JLabel("State:");
        state.setFont(new Font("RaleWay", Font.BOLD, 20));
        state.setBounds(100, 540, 200, 30);
        add(state);

        stateTextField = new JTextField();
        stateTextField.setFont(new Font("RaleWay", Font.BOLD, 16));
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);

        JLabel pincode = new JLabel("Pin Code:");
        pincode.setFont(new Font("RaleWay", Font.BOLD, 20));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);

        pinTextField = new JTextField();
        pinTextField.setFont(new Font("RaleWay", Font.BOLD, 16));
        pinTextField.setBounds(300, 590, 400, 30);
        add(pinTextField);

        nextBtn = new JButton("Next");
        nextBtn.setFont(new Font("Raleway", Font.BOLD, 14));
        nextBtn.setBounds(620, 660, 80, 30);
        nextBtn.addActionListener(this);
        add(nextBtn);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }
        String email = emailTextField.getText();
        String marital = null;
        if (married.isSelected()) {
            marital = "Married";
        } else if (unmarried.isSelected()) {
            marital = "Unmarried";
        } else if (other.isSelected()) {
            marital = "Other";
        }
        String address = addressTextField.getText();
        String city = cityTextField.getText();
        String state = stateTextField.getText();
        String pincode = pinTextField.getText();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Required");
            }
            else{
                Conn c= new Conn();
                // String query= "insert into signup values('" +formno+ "', "+name+"', "+ "', "+fname+"', "+dob+"', "+gender+"', "+email+"', "+marital+"', "+address+"', "+city+"', "+state+"', "+pincode+"');";
                String query = "insert into signup values('" + formno + "', '" + name + "', '" + fname + "', '" + dob + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city + "', '" + state + "', '" + pincode + "');";

                c.s.executeUpdate(query);

                setVisible(false);
                new SignupTwo(formno).setVisible(true);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        

    }

    public static void main() {
        new SignupOne();
    }
}
