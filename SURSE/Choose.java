package TemaPOO;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Choose extends JFrame implements ActionListener {

    ArrayList<Account> accounts;
    Character caracter;
    Grid array;
    Game g;
    ArrayList <String> forest, inamic, finish;
    JList list;
    JLabel label, image, email_label, password_label, message, mesaj;;
    JPanel panel, panelForButtons, panelForMoves, panelForPotions, panelMessage;
    JTextField email_text;
    JPasswordField password_text;
    Integer index, indexCh;
    JButton b1, b2, b3, b4, b5, submit;
    JButton[][] button;
    public Choose(ArrayList<Account> accounts, ArrayList < String> forest, ArrayList <String> inamic, ArrayList <String> finish) {

        // list retine conturile din json
        //daca este selectat un cont, este apelata functia login
        this.accounts = accounts;
        setSize(650,650);
        DefaultListModel model = new DefaultListModel();
        list = new JList(model);
        list.setVisibleRowCount(1);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    index = list.getSelectedIndex();
                    if ( index >= 0) {
                        login();
                    }
                }
            }
        });
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setBounds(50,100,180,300);
        int i = 0;
        for (Account st : accounts) {
            model.add(i, st.information.name);
            i++;
        }
        label = new JLabel();
        label.setPreferredSize(new Dimension(300,300));
        image = new JLabel();
        image.setLayout(new FlowLayout());
        //ninja.jpg este imaginea de background pentru pagina de start
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("ninja.jpg"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        // list si label apartin unui panel
        Image dimg = img.getScaledInstance(650, 650, Image.SCALE_SMOOTH);
        ImageIcon imageIcon  = new ImageIcon(dimg);
        image.setIcon(imageIcon);
        image.setLayout(new BorderLayout());
        validate();
        label.setText("Choose an account");
        label.setForeground(Color.white);
        label.setFont(new Font("Serif", Font.PLAIN, 18));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setOpaque(false);
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setBorder(new EmptyBorder(new Insets(400, 0, 100, 0)));
        panel.setLayout(boxlayout);
        panel.add(label);
        list.setBackground(new Color(3,17,40));
        list.setForeground(Color.white);
        panel.add(list);
        image.add(panel);
        add(image);
        setVisible(true);

  }
   public void login() {

        //are loc conectarea propriu-zisa
       //sunt eliminate label si list
       panel.remove(label);
       panel.remove(list);
       panel.revalidate();
       panel.repaint();
       image.remove(panel);
       image.revalidate();
       image.repaint();
       //camp pentru email
       email_label = new JLabel();
       email_label.setText("Email :");
       email_text = new JTextField();
       email_label.setForeground(Color.white);
       email_label.setSize(5,5);
       email_text.setSize(new Dimension(5,5));
       //camp petru parola
       password_label = new JLabel();
       password_label.setText("Password :");
       password_text = new JPasswordField();
       password_label.setForeground(Color.white);
       password_label.setSize(5,5);
       password_text.setSize(5,5);
       // buton Submit
       submit = new JButton("SUBMIT");
       submit.addActionListener(this);
       BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);
       panel.setLayout(boxlayout);
       panel.setBorder(new EmptyBorder(new Insets(400, 100, 180, 100)));
       panel.add(email_label);
       panel.add(email_text);
       panel.add(password_label);
       panel.add(password_text);
       panel.add(submit);
       boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
       panel.setLayout(boxlayout);
       // mesaj pentru autentificare
       message = new JLabel();
       message.setForeground(Color.white);
       panel.add(message);
       image.add(panel);

   }
   // adauga imagini butoanelor din grid
   public void buttons (JButton[][] button){

        for(int i = 0; i < array.latime; i++) {
           for (int j = 0; j < array.lungime; j++) {
               if (array.current.Ox == i && array.current.Oy == j) {
                   button[i][j].setIcon(image("64697be0705dd503415616a26162204b.jpg"));
               }
               else {
                   button[i][j].setIcon(image("HD-wallpaper-question-mark-question-mark-pattern-amoled-dark-pure-black-thumbnail.jpg"));
               }
           }
       }
    }
   // alegerea unui personaj
   public void personaj(){

        image.remove(panel);
       image.revalidate();
       image.repaint();
       DefaultListModel model = new DefaultListModel();
       label = new JLabel();
       label.setText("Choose a character");
       label.setForeground(Color.white);
       label.setFont(new Font("Serif", Font.PLAIN, 18));
       //list retine lista de personaje din json
       list = new JList(model);
       list.setVisibleRowCount(1);
       list.addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {
               if (!e.getValueIsAdjusting()) {
                   indexCh = list.getSelectedIndex();
                   if ( indexCh >= 0) {
                         GameInterface();
                   }
               }
           }
       });
       list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       list.setBounds(50,100,180,300);
       int i = 0;
       for (Character c : accounts.get(index).characters) {
           model.add(i, c);
           i++;
       }
       panel = new JPanel();
       panel.setOpaque(false);
       BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
       panel.setBorder(new EmptyBorder(new Insets(400, 0, 100, 0)));
       panel.setLayout(boxlayout);
       panel.add(label);
       list.setBackground(new Color(3,17,40));
       list.setForeground(Color.white);
       panel.add(list);
       image.add(panel);

   }
   // este creata interfata grafica pentru jocul propriu zis ( grid )
    public void GameInterface() {

        image.remove(panel);
        image.revalidate();
        image.repaint();
        remove(image);
        revalidate();
        repaint();
        array = Grid.generare(5, 5);
        setSize(618,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelForButtons = new JPanel();
        panelForButtons.setSize(new Dimension(618, 612));
        panelForButtons.setBackground(Color.BLACK);
        panelForButtons.setLayout(new GridLayout(5,5));
        setLayout(new GridLayout(6, 5));
         button = new JButton[array.latime][array.lungime];
        for(int i = 0; i < array.latime; i++) {
            for (int j = 0; j < array.lungime; j++) {
                button[i][j] = new JButton();
                button[i][j].setPreferredSize(new Dimension(120,120));
                panelForButtons.add(button[i][j]);
            }
        }
        buttons(button);
        panelForButtons.revalidate();
        setVisible(true);
        // butoanele petru N, V, E, V
        b1 = new JButton("N");
        b1.setBackground(Color.black);
        b1.setPreferredSize(new Dimension(50, 50));
        b2 = new JButton("S");
        b2.setBackground(Color.black);
        b3 = new JButton("E");
        b3.setBackground(Color.black);
        b4 = new JButton("V");
        b4.setBackground(Color.black);
        b5 = new JButton();
        b5.setBackground(Color.black);
        b1.setForeground(Color.white);
        b2.setForeground(Color.white);
        b3.setForeground(Color.white);
        b4.setForeground(Color.white);
        b5.setForeground(Color.white);
        b5.setBackground(Color.black);
        setBackground(new Color(128,128,128));
        b2.setPreferredSize(new Dimension(50, 50));
        b3.setPreferredSize(new Dimension(50, 50));
        b4.setPreferredSize(new Dimension(50, 50));
        b5.setPreferredSize(new Dimension(50, 50));
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        panelForMoves = new JPanel();
        // panelForMoves retine butoanele de N, V, E, S
        panelForMoves.setSize(300,300);
        panelForMoves.setLayout(new BorderLayout());
        panelForMoves.add(b1, BorderLayout.NORTH);
        panelForMoves.add(b2, BorderLayout.SOUTH);
        panelForMoves.add(b3, BorderLayout.EAST);
        panelForMoves.add(b4, BorderLayout.WEST);
        panelForMoves.add(b5, BorderLayout.CENTER);
        panelForMoves.setBackground(new Color(128,128,128));
        caracter = accounts.get(index).characters.get(indexCh);
        for(int i = 0; i < array.latime; i++)
            for(int j = 0; j < array.lungime; j++)
                button[i][j].setVisible(true);
        setLayout(new FlowLayout());
        add(panelForButtons);
        add(panelForMoves);
        validate();

    }
    // returneaza imaginea subforma ImageIcon
    public ImageIcon image ( String path) {

        BufferedImage img = null;
        try{
            img = ImageIO.read(new File(path));
        }
        catch (IOException h) {
            h.printStackTrace();
        }
        Image dimg = img.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        ImageIcon imageIcon  = new ImageIcon(dimg);
        return imageIcon;
    }

    public void play(){
        // celula curenta = 2 -> SHOP
          if ( caracter.current_life > 0) {
              if (array.current.celltype == 2) {
                  ShopFrame f = new ShopFrame(((Shop)array.current.cellelement).potions, array, caracter);
              }
              else {
                  //celule curenta = 1 -> ENEMY
                  if ( array.current.celltype == 1) {
                      Random rr = new Random();
                      //se afiseaza o poevste din json
                      SimpleFrame s = new SimpleFrame(Game.inamic.get(rr.nextInt(Game.inamic.size())));
                      Timer t = new Timer(3000, null);
                      t.start();
                      t.addActionListener(new ActionListener() {
                          @Override
                          public void actionPerformed(ActionEvent e) {
                              s.setVisible(false);
                              s.dispose();
                              EnemyFrame ee = new EnemyFrame((Enemy)array.current.cellelement, caracter, array);
                          }
                      });
                      t.setRepeats(false);
                  }
                  else {
                      //celula curenta = 0 -> PADURE
                      if ( array.current.celltype == 0) {
                          Random rr = new Random();
                          // se afiseaza o poveste din json
                          SimpleFrame s = new SimpleFrame(Game.forest.get(rr.nextInt(Game.forest.size())));
                          Timer t = new Timer(3000, null);
                          t.start();
                          t.addActionListener(new ActionListener() {
                              @Override
                              public void actionPerformed(ActionEvent e) {
                                  s.setVisible(false);
                                  s.dispose();
                              }
                          });
                          t.setRepeats(false);
                          Random rand = new Random();
                          if( rand.nextInt(101) <= 20) {
                              caracter.inventory.money++;
                          }
                      }
                      else {
                          //celula curenta = 3 -> FINISH
                          if ( array.current.celltype == 3) {
                              Finally f = new Finally(1, caracter);
                              dispose();
                              setVisible(false);
                              validate();
                          }
                      }
                  }
              }
          }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String email = email_text.getText();
            String password = password_text.getText();
            // autentificare corecta
            if (email.equals(accounts.get(index).information.credentials.getEmail()) && password.equals(accounts.get(index).information.credentials.getPass())) {
                message.setText(" WELCOME, " + email + "! Let's game!");
                personaj();
            } else {
                message.setText(" Invalid user.. ");
            }
        } else {
            //actualizare buton. Se afla pe casuta x, apare imaginea corespunzatoare
            if ( array.current.celltype == 2) {
                button[array.current.Ox][array.current.Oy].setIcon(image("descărcare.jpg"));
            }
            else {
                button[array.current.Ox][array.current.Oy].setIcon(image("0_C__knszvhJ6NCa11.jpg"));
            }
             //deplasare
            if (e.getSource() == b1) {
                array.goNorth();
            } else {
                if (e.getSource() == b2) {
                    array.goSouth();
                } else {
                    if (e.getSource() == b3) {
                        array.goEast();
                    } else {
                        if (e.getSource() == b4) {
                            array.goWest();
                        }
                    }
                }
                if (array.current.cellelement != null) {
                    if (array.current.cellelement instanceof Shop) {
                        button[array.current.Ox][array.current.Oy].setIcon(image("images.jpg"));
                    }
                    else {
                        button[array.current.Ox][array.current.Oy].setIcon(image("64697be0705dd503415616a26162204b.jpg"));
                    }
                }
                else {
                    button[array.current.Ox][array.current.Oy].setIcon(image("64697be0705dd503415616a26162204b.jpg"));
                }
                play();
            }
        }
    }
}
