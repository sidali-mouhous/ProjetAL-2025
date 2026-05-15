package org.example;

import fr.univ_rouen.draw.editor.EditeurFrame;
import fr.univ_rouen.draw.editor.Interpreteur;
import fr.univ_rouen.draw.model.Dessin;

public class Main {
    public static void main(String[] args) {
        Dessin monDessin = new Dessin();
        EditeurFrame maFrame = new EditeurFrame(monDessin);

        Interpreteur interpreteur = new Interpreteur(monDessin, maFrame);
        interpreteur.lancer();
    }
}
