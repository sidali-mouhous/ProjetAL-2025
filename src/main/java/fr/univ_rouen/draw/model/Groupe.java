package fr.univ_rouen.draw.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Groupe implements Forme {
    private String nom;
    private List<Forme> enfants;

    public Groupe(String nom) {
        this.nom = nom;
        this.enfants = new ArrayList<>();
    }

    public void ajouter(Forme f) {
        this.enfants.add(f);
    }

    public void supprimer(Forme f) {
        this.enfants.remove(f);
    }

    public List<Forme> getEnfants() {
        return enfants;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void dessiner(Graphics g) {
        for (Forme f : enfants) {
            f.dessiner(g);
        }
    }

    @Override
    public String toString() {
        return "group [" + nom + "]";
    }
}