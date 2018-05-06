package GIU;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Okno extends JFrame {
    private final int długość = 30;
    private int aktualnyIndeks = 0;
    Okno(String nazwa, int dlugosc, int szerokosc){
        super(nazwa);
        setResizable(false);
        setLayout(new BorderLayout());
        setSize(dlugosc,szerokosc);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List list=new List(4,true);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                aktualnyIndeks=list.getSelectedIndex();
                System.out.println("aktualny indeks to: "+aktualnyIndeks);
                list.deselect(aktualnyIndeks);
            }
        });
        JPanel p=new JPanel(new FlowLayout());
        JTextField t=new JTextField("Liczba znaków: "+String.valueOf(długość));
        JButton b=new JButton("Dodaj");
        JButton b1=new JButton("Góra");
        b.setFont(new Font("Courier", Font.BOLD, 14));
        b.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent ke) {
                    if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                        list.add(t.getText().toUpperCase());
                    }
            }
        });
        b.addActionListener(e -> list.add(t.getText().toUpperCase())); // zeby nie bylo ze tylko enter to da ci samo klikanie
        b1.setFont(new Font("Courier", Font.BOLD, 14));
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String koniec=list.getItem(0);
                list.replaceItem(list.getItem(aktualnyIndeks),0);
                list.add(koniec);
                list.remove(aktualnyIndeks);
            }
        });
        JLabel l=new JLabel("Oto piosenki");
        p.add(t);
        p.add(l);
        p.add(list);
        p.add(b);
        p.add(b1);
        add(p);
        setVisible(true);
    }
}