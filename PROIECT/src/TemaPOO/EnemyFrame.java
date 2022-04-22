package TemaPOO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class EnemyFrame extends JFrame implements ActionListener {

    Enemy enemy;
    Grid array;
    Character caracter;
    GridBagConstraints gbc;
    JButton b1, b2, b3, bb, bbb;
    JPanel potionsPanel;

    public EnemyFrame(Enemy enemy, Character caracter, Grid array) {

        //fereastra in lupta cu inamicul : butoanele: ataca inaic, foloseste potiune, foloseste abilitate
        this.array = array;
        this.enemy = enemy;
        this.caracter = caracter;
        setSize(612, 618);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx =  0;
        gbc.gridy = 0;
        // bb si bbb retin viata si mana curenta pentru caracter si inamic
        bb = new JButton("Caracter: Current life : "  + caracter.current_life + " Current mana: " + caracter.current_mana );
        bb.setPreferredSize(new Dimension(300,50));
        add(bb, gbc);
        bb.setForeground(Color.white);
        bb.setBackground(Color.black);
        bbb = new JButton("Enemy: Current life : "  + enemy.current_life + " Current mana: " + enemy.current_mana);
        bbb.setPreferredSize(new Dimension(300,50));
        bbb.setForeground(Color.white);
        bbb.setBackground(Color.black);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(bbb, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        b1 = new JButton("Ataca inamic");
        b1.setPreferredSize(new Dimension(300,50));
        add(b1, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        b2 = new JButton("Foloseste Abilitate");
        b2.setPreferredSize(new Dimension(300,50));
        add(b2, gbc);
        if ( caracter.inventory.potions.size() != 0) {
            b3 = new JButton("Foloseste potiune");
            gbc.gridx = 0;
            gbc.gridy = 4;
            add(b3, gbc);
            b3.setPreferredSize(new Dimension(300,50));
            b3.addActionListener(this);
            b3.setForeground(Color.white);
            b3.setBackground(Color.black);
        }
        b1.addActionListener(this);
        b2.addActionListener(this);
        b1.setBackground(Color.black);
        b2.setBackground(Color.black);
        b1.setForeground(Color.white);
        b2.setForeground(Color.white);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1){
            enemy.receiveDamage(caracter.getDamage());
        }
        else {
            JPanel spellsPanel = new JPanel();
            spellsPanel.setLayout(new FlowLayout());
            JButton[] spells = new JButton[10];
            int i = 0;
            //spells panel retine spell urile personajului
            if (e.getSource() == b2) {
                for (Spell spell : caracter.spells) {
                    System.out.println(spell.getClass());
                    spells[i] = new JButton(spell.getClass().getName().substring(8) + spell.cost_mana + " cost damage  " + spell.cost_damage);
                    spells[i].setForeground(Color.white);
                    spells[i].setBackground(Color.black);
                    spells[i].addActionListener(this);
                    spellsPanel.add(spells[i]);
                    spellsPanel.setSize(900, 50);
                    ++i;
                }
                gbc.gridx = 0;
                gbc.gridy = 5;
                add(spellsPanel, gbc);
                validate();
            }
            else {
                //potions panel retine potiunile cumparate
                if (e.getSource() == b3) {
                    potionsPanel = new JPanel();
                    potionsPanel.setLayout(new FlowLayout());
                    JButton[] potiuni = new JButton[10];
                    i = 0;
                    for (Potion p : caracter.inventory.potions) {
                        potiuni[i] = new JButton(p.getClass().getName().substring(8) + p.valueOfprice() + " " + p.valueOfweight() + " " + p.valueOfregeneration());
                        potionsPanel.add(potiuni[i]);
                        potiuni[i].setForeground(Color.white);
                        potiuni[i].setBackground(Color.black);
                        potiuni[i].addActionListener(this);
                        ++i;
                    }
                    gbc.gridx = 0;
                    gbc.gridy = 7;
                    add(potionsPanel, gbc);
                    validate();
                } else {
                    if (caracter.inventory.potions.size() > 0 )
                    for (Potion p : caracter.inventory.potions) {
                        if (( (JButton) e.getSource()).getText().equals(p.getClass().getName().substring(8) + p.valueOfprice() + " " + p.valueOfweight() + " " + p.valueOfregeneration())) {
                            caracter.use_potion(p);
                            remove(potionsPanel);
                            validate();
                            repaint();
                            break;
                        }
                    }
                    for (Spell spell : caracter.spells) {
                        if ( ((JButton) e.getSource()).getText().equals(spell.getClass().getName().substring(8) + spell.cost_mana + " cost damage  " + spell.cost_damage)) {
                            caracter.use_abilities(spell, enemy);
                            remove(spellsPanel);
                            validate();
                            repaint();
                        }
                    }
                    bb.setText("Caracter Current life :"  + caracter.current_life + "Current mana: " + caracter.current_mana );
                    bbb.setText("Enemy Current life :"  + enemy.current_life + "Current mana: " + enemy.current_mana);
                }
            }
        }
        if (enemy.current_life > 0 && caracter.current_life > 0) {
            if (e.getSource() == b1 || (e.getSource() != b2 &&  e.getSource() != b3) ) {
                Random rr = new Random();
                int ataca = rr.nextInt(101);
                if (ataca < 75) {
                    caracter.receiveDamage(enemy.getDamage());
                } else {
                    enemy.use_abilities(enemy.spells.get(rr.nextInt(3)), caracter);
                }
                bb.setText("Caracter Current life :" + caracter.current_life + "Current mana: " + caracter.current_mana);
                bbb.setText("Enemy Current life :" + enemy.current_life + "Current mana: " + enemy.current_mana);
            }
        }
        if (enemy.current_life <= 0) {
            bb.setText("Caracter Current life :"  + caracter.current_life + "Current mana: " + caracter.current_mana );
            bbb.setText("Enemy Current life :"  + enemy.current_life + "Current mana: " + enemy.current_mana);
            gbc.gridx = 0;
            gbc.gridy = 8;
            JButton j = new JButton("Ai castigat");
            j.setBackground(Color.black);
            j.setForeground(Color.white);
            add(j, gbc);
            array.current.cellelement = null;
            array.current.celltype = 0;
            Random random = new Random();
            if ( random.nextInt(100) < 80 ) {
                caracter.inventory.money ++;
            }
            Timer t = new Timer(3000, null);
            t.start();
            t.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    validate();
                }
            });
            t.setRepeats(false);
        }
        else {
            if (caracter.current_life <= 0) {
                Finally f = new Finally(0, caracter);
                validate();
                dispose();
                validate();
            }
        }
    }
}
