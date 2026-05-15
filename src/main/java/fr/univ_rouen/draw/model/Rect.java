package fr.univ_rouen.draw.model;

import java.awt.Graphics;
import java.awt.Color;

public class Rect implements Forme {
    private int x1, y1, x2, y2;
    private String couleur;

    public Rect(int x1, int y1, int x2, int y2, String couleur) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.couleur = couleur;
    }

    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }
    public String getCouleur() { return couleur; }

    @Override
    public void dessiner(Graphics g) {
        if (couleur.equalsIgnoreCase("blue")) g.setColor(Color.BLUE);
        else if (couleur.equalsIgnoreCase("green")) g.setColor(Color.GREEN);
        else if (couleur.equalsIgnoreCase("red")) g.setColor(Color.RED);
        else g.setColor(Color.BLACK);

        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        g.drawRect(x, y, width, height);
    }

    @Override
    public String toString() {
        return "rect " + x1 + " " + y1 + " " + x2 + " " + y2 + " " + couleur;
    }
}