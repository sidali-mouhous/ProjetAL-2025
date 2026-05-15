package fr.univ_rouen.draw.model;

import java.awt.Graphics;
import java.awt.Color;

public class Ellipse implements Forme {
    private int x, y, rx, ry;
    private String couleur;

    public Ellipse(int x, int y, int rx, int ry, String couleur) {
        this.x = x;
        this.y = y;
        this.rx = rx;
        this.ry = ry;
        this.couleur = couleur;
    }

    @Override
    public void dessiner(Graphics g) {
        if (couleur.equalsIgnoreCase("blue")) g.setColor(Color.BLUE);
        else if (couleur.equalsIgnoreCase("green")) g.setColor(Color.GREEN);
        else if (couleur.equalsIgnoreCase("red")) g.setColor(Color.RED);
        else g.setColor(Color.BLACK);

        g.drawOval(x - rx, y - ry, 2 * rx, 2 * ry);
    }

    @Override
    public String toString() {
        return "elli " + x + " " + y + " " + rx + " " + ry + " " + couleur;
    }
}
