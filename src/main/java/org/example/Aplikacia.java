package org.example;

import javax.swing.*;
import java.awt.*;

public class Aplikacia {

    public Aplikacia(){
        JFrame aplikacia= new JFrame("Kreslenie");
        aplikacia.setResizable(false);
        aplikacia.setSize(700,700);
        aplikacia.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Plocha plocha=new Plocha(Color.RED);

        JPanel menu=new JPanel();
        JLabel mod=new JLabel(Mod.KRESLENIE.toString());
        JButton strom=new JButton("Strom");
        JButton presun=new JButton("Presun");
        JButton zmenaFarby=new JButton("Dalsia farba");
        mod.setBackground(Color.RED);
        mod.setOpaque(true);

        menu.setLayout(new GridLayout(1,4));
        menu.add(strom);
        menu.add(presun);
        menu.add(zmenaFarby);
        menu.add(mod);
        aplikacia.add(plocha,BorderLayout.CENTER);
        aplikacia.add(menu,BorderLayout.SOUTH);

        Priebeh priebeh=new Priebeh(plocha,mod);

        zmenaFarby.addActionListener(priebeh);
        strom.addActionListener(priebeh);
        presun.addActionListener(priebeh);

        plocha.addMouseListener(priebeh);
        plocha.addMouseMotionListener(priebeh);

        aplikacia.setVisible(true);
    }
}
