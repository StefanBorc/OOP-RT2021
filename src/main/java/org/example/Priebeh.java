package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Priebeh extends UniverzalnyAdapter{
    public static final String STROM="Strom";
    public static final String PRESUN="Presun";
    public static final String FARBA="Dalsia farba";
    private JLabel mod;
    private Plocha plocha;

    private Mod typModu;
    private ArrayList<Color> farby;
    private Point zaciatok;
    private Point koniec;
    public Priebeh(Plocha plocha, JLabel mod) {

        this.mod=mod;
        this.plocha=plocha;
        inicializujFarby();
        typModu=Mod.KRESLENIE;
        zaciatok=new Point();
        koniec=new Point();
    }

    private void inicializujFarby(){
        farby=new ArrayList<>();
        farby.add(Color.RED);
        farby.add(Color.BLUE);
        farby.add(Color.YELLOW);
    }
    private void zmenFarby(){
        farby.add(farby.get(0));
        farby.remove(0);
        plocha.setFarba(farby.get(0));
    }
    private void aktualizujLabel(){
        mod.setBackground(farby.get(0));
        mod.setText(typModu.toString());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(STROM)){
            typModu=Mod.KRESLENIE;
        }
        else if(e.getActionCommand().equals(PRESUN)){
            typModu=Mod.PRESUVANIE;
        }
        else if(e.getActionCommand().equals(FARBA)){
            zmenFarby();
        }
        aktualizujLabel();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        zaciatok=e.getPoint();
        koniec=e.getPoint();
        if(typModu.equals(Mod.KRESLENIE)){
            plocha.pridajStrom(e,this);

        }
        else{
            plocha.setUtvar(plocha.dajStrom(e));
            if(plocha.getUtvar()!=null){
                zaciatok.x=koniec.x-plocha.getUtvar().getX();
                zaciatok.y=koniec.y-plocha.getUtvar().getY();
            }
        }
        plocha.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        koniec=e.getPoint();
        if(typModu.equals(Mod.KRESLENIE)){
            plocha.getUtvar().setX(Math.min(koniec.x,zaciatok.x));
            plocha.getUtvar().setY(Math.min(koniec.y,zaciatok.y));
            plocha.getUtvar().setSirka(Math.abs(koniec.x- zaciatok.x));
            plocha.getUtvar().setVyska(Math.abs(koniec.y- zaciatok.y));
        }
        else{
            if(plocha.getUtvar()!=null){
                plocha.getUtvar().setX(koniec.x- zaciatok.x);
                plocha.getUtvar().setY(koniec.y- zaciatok.y);
            }
        }

        plocha.repaint();
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        koniec.x=0;
        koniec.y=0;
        zaciatok.x=0;
        zaciatok.y=0;
        plocha.setUtvar(null);
    }

}
