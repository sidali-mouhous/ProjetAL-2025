package fr.univ_rouen.draw.model;

import java.util.ArrayList;
import java.util.List;

public class Dessin {
    private List<Forme> formes;

    public Dessin() {
        this.formes = new ArrayList<>();
    }

    public void ajouterForme(Forme f) {
        this.formes.add(f);
    }

    public List<Forme> getFormes() {
        return formes;
    }

    public void vider() {
        this.formes.clear();
    }
}