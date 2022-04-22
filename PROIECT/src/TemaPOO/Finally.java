package TemaPOO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Finally extends JFrame {

    JButton button;
    JButton b;
    Character caracter;

    public Finally(int type, Character caracter) {
        setSize(600, 612);
        setLayout(new FlowLayout());
        this.caracter = caracter;
        button = new JButton();
        button.setForeground(Color.white);
        button.setBackground(Color.black);
        add(button);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (type == 1) {
            button.setText("Ai castigat");
        } else {
            button.setText("Ai pierdut");
        }
        caracter.experience = caracter.experience + 20;
        if (caracter.experience >= 50) {
            caracter.level++;
            caracter.experience = 0;
        }
        b = new JButton();
        b.setText("Level: " + caracter.level + " Experience: " + caracter.experience);
        add(b);
        b.setBackground(Color.black);
        b.setForeground(Color.white);
        Timer t = new Timer(5000, null);
        t.start();
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        t.setRepeats(false);
        setVisible(true);

    }
}
