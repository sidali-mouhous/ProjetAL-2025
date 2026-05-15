package fr.univ_rouen.draw.model;

import java.awt.Graphics;
import java.awt.Color;

public class circ implements Forme {
    private int x, y, r;
    private String couleur;

    public circ(int x, int y, int r, String couleur) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.couleur = couleur;
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
        return "circle " + x + " " + y + " " + r + " " + couleur;
    }
}