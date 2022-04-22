package TemaPOO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShopFrame extends JFrame implements ActionListener {

    ArrayList<Potion> potions;
    Character caracter;
    Grid array;
    JPanel panelForPotions;
    JButton mesaj, money;
    JPanel panelMessage;
    JButton[] potiune;
    GridBagConstraints gbc;
    public ShopFrame(ArrayList<Potion> potions, Grid array, Character caracter) {

        setSize(300,300);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        this.caracter = caracter;
        this.potions = potions;
        int i = 0;
        this.array = array;
        potiune = new JButton[10];
        panelForPotions = new JPanel(new GridBagLayout());
        for (Potion p : ((Shop) array.current.cellelement).potions) {
            potiune[i] = new JButton(p.getClass().getName().substring(8) + + p.valueOfprice() + " weight: " + p.valueOfweight() + " regeneration: " + p.valueOfregeneration());
            gbc.gridx = 0;
            gbc.gridy = i;
            potiune[i].setForeground(Color.white);
            potiune[i].setBackground(Color.black);
            potiune[i].addActionListener(this);
            panelForPotions.add(potiune[i], gbc);
            ++i;
        }
        add(panelForPotions, gbc);
        setVisible(true);
        money = new JButton("Monede : " + caracter.inventory.money + " Spatiu ramas: "  + caracter.inventory.weight_remain());
        money.setForeground(Color.white);
        money.setBackground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(money, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        mesaj = new  JButton();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelForPotions.add(mesaj, gbc);
        repaint();
        validate();
        money.setText("Monede : " + caracter.inventory.money + " Spatiu ramas: "  + caracter.inventory.weight_remain() );
        for (Potion p : ((Shop) array.current.cellelement).potions) {
            if (((JButton) e.getSource()).getText().equals(p.getClass().getName().substring(8) + +p.valueOfprice() + " weight: " + p.valueOfweight() + " regeneration: " + p.valueOfregeneration())) {
                mesaj.setText((String) caracter.buy_potion(p));
                money.setText("Coins : " + caracter.inventory.money);
            }
        }
        mesaj.setForeground(Color.white);
        mesaj.setBackground(Color.black);

    }
}
