package fr.univ_rouen.draw.model;

import java.awt.Graphics;

public interface Forme {
    void dessiner(Graphics g);
    String toString();
}