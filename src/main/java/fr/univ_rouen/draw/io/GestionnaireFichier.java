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
        writer.println("<dessin>");
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
        } else {
            writer.println(indent + "<forme type=\"" + f.getClass().getSimpleName().toLowerCase() + "\">"
                    + f.toString() + "</forme>");
        }
    }

    public static void charger(Dessin dessin, String nomFichier, Interpreteur interpreteur) throws IOException {
        File fichier = new File(nomFichier);
        if (!fichier.exists()) {
            System.out.println("Fichier introuvable : " + fichier.getPath());
            return;
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
            } else if (ligne.contains("<forme")) {
                String contenu = ligne.substring(ligne.indexOf(">") + 1, ligne.lastIndexOf("<"));
                String[] mots = contenu.split(" ");
                Dessin temp = new Dessin();
                new Interpreteur(temp, null).executer(mots[0], mots);
                if (!temp.getFormes().isEmpty()) {
                    ajouter(conteneur, temp.getFormes().get(0));
                }
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
