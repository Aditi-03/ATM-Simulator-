package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// import java.util.*;
public class SignupTwo extends JFrame implements ActionListener {

    JTextField panTextField, aadharTextField;
    JRadioButton yesone, noone, yestwo, notwo;
    JButton nextBtn, back;
    JComboBox religion, category, incomeCat, education, occComboBox;
    String formno;
    SignupOne signupOneInstance;

    SignupTwo(String formno, SignupOne signupOneInstance) {
        this.formno = formno;
        this.signupOneInstance = signupOneInstance;
        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE2");

        JLabel additionalDetails = new JLabel("Page2: Additional Details");
        additionalDetails.setFont(new Font("RaleWay", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);

        JLabel Religion = new JLabel("Religion:");
        Religion.setFont(new Font("RaleWay", Font.BOLD, 20));
        Religion.setBounds(100, 140, 400, 30);
        add(Religion);

        String valReligion[] = { "Hindu", "Muslim", "Sikh", "Christian", "Other" };
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        add(religion);

        JLabel Category = new JLabel("Category:");
        Category.setFont(new Font("RaleWay", Font.BOLD, 20));
        Category.setBounds(100, 190, 400, 30);
        add(Category);

        String valCategory[] = { "General", "SC", "ST", "OBC", "Other" };
        category = new JComboBox(valCategory);
        category.setBounds(300, 190, 400, 30);
        add(category);

        JLabel income = new JLabel("Income:");
        income.setFont(new Font("RaleWay", Font.BOLD, 20));
        income.setBounds(100, 240, 400, 30);
        add(income);

        String valIncome[] = { "NULL", "<1,50,000", "2,50,000", "5,00,000", "Upto 10,00,000" };
        incomeCat = new JComboBox(valIncome);
        incomeCat.setBounds(300, 240, 400, 30);
        add(incomeCat);

        JLabel qualification = new JLabel("Qualification:");
        qualification.setFont(new Font("RaleWay", Font.BOLD, 20));
        qualification.setBounds(100, 290, 200, 30);
        add(qualification);

        String educationVal[] = { "Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others" };
        education = new JComboBox(educationVal);
        education.setBounds(300, 290, 400, 30);
        add(education);

        JLabel occupation = new JLabel("Occupation:");
        occupation.setFont(new Font("RaleWay", Font.BOLD, 20));
        occupation.setBounds(100, 340, 200, 30);
        add(occupation);

        String occupationVal[] = { "Salaried", "Self-Employed", "Bussiness", "Retired", "Others" };
        occComboBox = new JComboBox(occupationVal);
        occComboBox.setBounds(300, 340, 400, 30);
        add(occComboBox);

        JLabel panno = new JLabel("PAN Number:");
        panno.setFont(new Font("RaleWay", Font.BOLD, 20));
        panno.setBounds(100, 390, 200, 30);
        add(panno);

        panTextField = new JTextField();
        panTextField.setFont(new Font("RaleWay", Font.PLAIN, 14));
        panTextField.setBounds(300, 390, 400, 30);
        add(panTextField);

        JLabel aadharno = new JLabel("Aadhar Number:");
        aadharno.setFont(new Font("RaleWay", Font.BOLD, 20));
        aadharno.setBounds(100, 440, 200, 30);
        add(aadharno);

        aadharTextField = new JTextField();
        aadharTextField.setFont(new Font("RaleWay", Font.PLAIN, 14));
        aadharTextField.setBounds(300, 440, 400, 30);
        add(aadharTextField);

        JLabel scitizen = new JLabel("Senior Citizen:");
        scitizen.setFont(new Font("RaleWay", Font.BOLD, 20));
        scitizen.setBounds(100, 490, 200, 30);
        add(scitizen);

        yesone = new JRadioButton("Yes");
        yesone.setBounds(300, 490, 60, 30);
        add(yesone);

        noone = new JRadioButton("No");
        noone.setBounds(450, 490, 60, 30);
        add(noone);

        ButtonGroup citizerGroup = new ButtonGroup();
        citizerGroup.add(yesone);
        citizerGroup.add(noone);

        // JLabel exists = new JLabel("Existing Account");
        // exists.setFont(new Font("RaleWay", Font.BOLD, 20));
        // exists.setBounds(100, 540, 200, 30);
        // add(exists);

        // yestwo = new JRadioButton("Yes");
        // yestwo.setBounds(300, 540, 60, 30);
        // add(yestwo);

        // notwo = new JRadioButton("No");
        // notwo.setBounds(450, 540, 60, 30);
        // add(notwo);

        back = new JButton("Back");
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBounds(540, 660, 80, 30);
        back.addActionListener(this);
        add(back);

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

        if (e.getSource() == nextBtn) {
            String sreligion = (String) religion.getSelectedItem();
            String scategory = (String) category.getSelectedItem();
            String income = (String) incomeCat.getSelectedItem();
            String qualification = (String) education.getSelectedItem();
            String occupation = (String) occComboBox.getSelectedItem();
            String pan_noString = panTextField.getText();
            String aadharString = aadharTextField.getText();
            String scitizen = null;
            if (yesone.isSelected()) {
                scitizen = "Yes";
            } else if (noone.isSelected()) {
                scitizen = "No";
            }

            try {

                Conn c = new Conn();

                String query = "insert into signuptwo values('" + formno + "', '" + sreligion + "', '" + scategory
                        + "', '"
                        + income + "', '" + qualification + "', '" + occupation + "', '" + pan_noString + "', '"
                        + aadharString
                        + "', '" + scitizen + "');";

                c.s.executeUpdate(query);
                setVisible(false);
                new SignupThree(formno).setVisible(true);

            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            signupOneInstance.setVisible(true);

        }

    }

    public static void main(String[] args) {
        new SignupTwo("", new SignupOne());
    }

}
