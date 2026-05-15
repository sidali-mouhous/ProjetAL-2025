package fr.univ_rouen.draw.model;

import java.awt.Graphics;
import java.awt.Color;

public class Ligne implements Forme {
    private int x1, y1, x2, y2;
    private String couleur;

    public Ligne(int x1, int y1, int x2, int y2, String couleur) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.couleur = couleur;
    }

    @Override
    public void dessiner(Graphics g) {
        if (couleur.equalsIgnoreCase("blue")) g.setColor(Color.BLUE);
        else if (couleur.equalsIgnoreCase("green")) g.setColor(Color.GREEN);
        else if (couleur.equalsIgnoreCase("red")) g.setColor(Color.RED);
        else g.setColor(Color.BLACK);

        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public String toString() {
        return "line " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + couleur;
    }
}