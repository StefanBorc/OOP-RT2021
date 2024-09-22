package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Plocha extends JPanel {
    @Setter
    private Color farba;
    @Getter@Setter
    private Utvar utvar;
    private ArrayList<Utvar> utvary;
    public Plocha(Color farba) {
        this.farba=farba;
        utvary=new ArrayList<>();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Utvar utvar:utvary){
            g.setColor(farba);
            utvar.nakresliUtvar(g);
        }
    }
    protected void pridajStrom(MouseEvent e,Priebeh priebeh){
         utvar=new Utvar(farba,e,priebeh);
         utvary.add(utvar);
    }
    protected Utvar dajStrom(MouseEvent e){
        for(Utvar utvar : utvary){
            if(utvar.jeStrom(e)){
                return utvar;
            }
        }
        return null;
    }
}
