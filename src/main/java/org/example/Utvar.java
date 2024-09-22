package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class Utvar extends JPanel  {
    private Color farba;
    @Setter@Getter
    private int x;
    @Setter@Getter
    private int y;

    @Setter
    private int sirka;
    @Setter
    private int vyska;
    private Priebeh priebeh;
    public Utvar(Color farba, MouseEvent e,Priebeh priebeh) {
        this.farba=farba;
        this.x=e.getX();
        this.y=e.getY();
        sirka=0;
        vyska=0;
    }

    protected void nakresliUtvar(Graphics g){
        g.setColor(farba);
        g.fillOval(x,y-vyska/2,sirka,vyska);
        g.fillRect(x+sirka/3, y, sirka/3, vyska);
    }
    protected boolean jeStrom(MouseEvent e){
        return e.getX()>x &&
                e.getX()<x+sirka &&
                e.getY()> y-vyska/2 &&
                e.getY()<y+vyska ;
    }


}
