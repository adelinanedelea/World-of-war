package TemaPOO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JPanel panel;
    JLabel email_label, password_label, message;
    JTextField email_text;
    JPasswordField password_text;
    JButton submit, cancel;
    Account a;
    public Login(Account a) {
        // Username Label
        this.a = a;
        email_label = new JLabel();
        email_label.setText("Email :");
        email_text = new JTextField(18);
        // Password Label
        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField(18);
        // Submit
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(email_label);
        panel.add(email_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Adding the listeners to components..
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle("Please Login Here !");
        setSize(450,350);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String email = email_text.getText();
        String password = password_text.getText();
        if (email.equals(a.information.credentials.getEmail()) && password.equals(a.information.credentials.getPass())) {
            message.setText(" Hello " + email + "");
        } else {
            message.setText(" Invalid user.. ");
        }
    }
}