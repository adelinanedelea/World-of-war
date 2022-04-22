package TemaPOO;

import javax.swing.*;
import java.awt.*;

public class SimpleFrame extends JFrame  {

    JButton jbutton;
    public SimpleFrame (String text) {

        setSize(300,300);
        jbutton = new JButton(text);
        jbutton.setPreferredSize(new Dimension(300, 300));
        jbutton.setBackground(Color.black);
        jbutton.setForeground(Color.white);
        add(jbutton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible( true);

    }

}
