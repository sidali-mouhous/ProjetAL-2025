package fr.univ_rouen.draw.io;

import fr.univ_rouen.draw.model.*;
import fr.univ_rouen.draw.editor.Interpreteur;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionnaireFichier {

    public static void sauvegarder(Dessin dessin, String nomFichier) throws IOException {
        File fichier = new File(nomFichier);
        if (fichier.getParentFile() != null) fichier.getParentFile().mkdirs();
        PrintWriter writer = new PrintWriter(fichier);
        writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.println("<dessin xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        writer.println("        xsi:noNamespaceSchemaLocation=\"dessin.xsd\">");
        for (Forme f : dessin.getFormes()) {
            ecrireXML(writer, f, "  ");
        }
        writer.println("</dessin>");
        writer.close();
    }

    private static void ecrireXML(PrintWriter writer, Forme f, String indent) {
        if (f instanceof Groupe) {
            Groupe g = (Groupe) f;
            writer.println(indent + "<groupe nom=\"" + g.getNom() + "\">");
            for (Forme enfant : g.getEnfants()) {
                ecrireXML(writer, enfant, indent + "  ");
            }
            writer.println(indent + "</groupe>");
        } else if (f instanceof Line) {
            Line l = (Line) f;
            writer.println(indent + "<line x1=\"" + l.getX1() + "\" y1=\"" + l.getY1()
                + "\" x2=\"" + l.getX2() + "\" y2=\"" + l.getY2()
                + "\" color=\"" + l.getCouleur() + "\"/>");
        } else if (f instanceof Rect) {
            Rect r = (Rect) f;
            writer.println(indent + "<rect x1=\"" + r.getX1() + "\" y1=\"" + r.getY1()
                + "\" x2=\"" + r.getX2() + "\" y2=\"" + r.getY2()
                + "\" color=\"" + r.getCouleur() + "\"/>");
        } else if (f instanceof Circ) {
            Circ c = (Circ) f;
            writer.println(indent + "<circ cx=\"" + c.getCx() + "\" cy=\"" + c.getCy()
                + "\" r=\"" + c.getR()
                + "\" color=\"" + c.getCouleur() + "\"/>");
        } else if (f instanceof Elli) {
            Elli e = (Elli) f;
            writer.println(indent + "<elli cx=\"" + e.getCx() + "\" cy=\"" + e.getCy()
                + "\" rx=\"" + e.getRx() + "\" ry=\"" + e.getRy()
                + "\" color=\"" + e.getCouleur() + "\"/>");
        }
    }

    public static void charger(Dessin dessin, String nomFichier, Interpreteur interpreteur) throws IOException {
        File fichier = new File(nomFichier);
        if (!fichier.exists()) {
            System.out.println("Fichier introuvable : " + fichier.getPath());
            return; // Returns safely without wiping the canvas
        }
        dessin.vider();
        List<String> lignes = new ArrayList<>();
        Scanner reader = new Scanner(fichier);
        while (reader.hasNextLine()) lignes.add(reader.nextLine().trim());
        reader.close();
        parserBloc(lignes, 0, dessin);
    }

    private static int parserBloc(List<String> lignes, int debut, Object conteneur) {
        int i = debut;
        while (i < lignes.size()) {
            String ligne = lignes.get(i);
            if (ligne.startsWith("</dessin>") || ligne.startsWith("</groupe>")) {
                return i + 1;
            } else if (ligne.startsWith("<groupe")) {
                String nom = extraireAttribut(ligne, "nom");
                Groupe g = new Groupe(nom);
                i = parserBloc(lignes, i + 1, g);
                ajouter(conteneur, g);
                continue;
            } else if (ligne.contains("<line ")) {
                ajouter(conteneur, new Line(
                    Integer.parseInt(extraireAttribut(ligne,"x1")),
                    Integer.parseInt(extraireAttribut(ligne,"y1")),
                    Integer.parseInt(extraireAttribut(ligne,"x2")),
                    Integer.parseInt(extraireAttribut(ligne,"y2")),
                    extraireAttribut(ligne,"color")));
            } else if (ligne.contains("<rect ")) {
                ajouter(conteneur, new Rect(
                    Integer.parseInt(extraireAttribut(ligne,"x1")),
                    Integer.parseInt(extraireAttribut(ligne,"y1")),
                    Integer.parseInt(extraireAttribut(ligne,"x2")),
                    Integer.parseInt(extraireAttribut(ligne,"y2")),
                    extraireAttribut(ligne,"color")));
            } else if (ligne.contains("<circ ")) {
                ajouter(conteneur, new Circ(
                    Integer.parseInt(extraireAttribut(ligne,"cx")),
                    Integer.parseInt(extraireAttribut(ligne,"cy")),
                    Integer.parseInt(extraireAttribut(ligne,"r")),
                    extraireAttribut(ligne,"color")));
            } else if (ligne.contains("<elli ")) {
                ajouter(conteneur, new Elli(
                    Integer.parseInt(extraireAttribut(ligne,"cx")),
                    Integer.parseInt(extraireAttribut(ligne,"cy")),
                    Integer.parseInt(extraireAttribut(ligne,"rx")),
                    Integer.parseInt(extraireAttribut(ligne,"ry")),
                    extraireAttribut(ligne,"color")));
            }
            i++;
        }
        return i;
    }

    private static void ajouter(Object conteneur, Forme f) {
        if (conteneur instanceof Dessin) ((Dessin) conteneur).ajouterForme(f);
        else if (conteneur instanceof Groupe) ((Groupe) conteneur).ajouter(f);
    }

    private static String extraireAttribut(String ligne, String attribut) {
        String recherche = attribut + "=\"";
        int debut = ligne.indexOf(recherche);
        if (debut < 0) return "";
        debut += recherche.length();
        int fin = ligne.indexOf("\"", debut);
        return fin >= 0 ? ligne.substring(debut, fin) : "";
    }
}