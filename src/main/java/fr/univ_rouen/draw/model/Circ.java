package fr.univ_rouen.draw.model;

import java.awt.Graphics;
import java.awt.Color;

public class Circ implements Forme {
    private int x, y, r;
    private String couleur;

    public Circ(int x, int y, int r, String couleur) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.couleur = couleur;
    }

    public int getCx() {
        return x;
    }

    public int getCy() {
        return y;
    }

    public int getR() {
        return r;
    }

    public String getCouleur() {
        return couleur;
    }

    @Override
    public void dessiner(Graphics g) {
        if (couleur.equalsIgnoreCase("blue")) g.setColor(Color.BLUE);
        else if (couleur.equalsIgnoreCase("green")) g.setColor(Color.GREEN);
        else if (couleur.equalsIgnoreCase("red")) g.setColor(Color.RED);
        else g.setColor(Color.BLACK);

        g.drawOval(x - r, y - r, 2 * r, 2 * r);
    }

    @Override
    public String toString() {
        return "circ " + x + " " + y + " " + r + " " + couleur;
    }
}