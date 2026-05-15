package fr.univ_rouen.draw.io;

import fr.univ_rouen.draw.model.*;
import fr.univ_rouen.draw.editor.Interpreteur;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class GestionnaireFichier {

    private static final String DOSSIER_VEC = "drawings";

    private static File resoudre(String nomFichier) {
        File dossier = new File(DOSSIER_VEC);
        if (!dossier.exists()) dossier.mkdirs();
        return new File(dossier, nomFichier);
    }

    public static void sauvegarder(Dessin dessin, String nomFichier) throws IOException {
        File fichier = resoudre(nomFichier);
        PrintWriter writer = new PrintWriter(fichier);
        writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        writer.println("<dessin>");

        for (Forme f : dessin.getFormes()) {
            writer.println("  " + formaterEnXML(f));
        }

        writer.println("</dessin>");
        writer.close();
    }

    private static String formaterEnXML(Forme f) {
        return "<forme type=\"" + f.getClass().getSimpleName().toLowerCase() + "\">"
                + f.toString() + "</forme>";
    }

    public static void charger(Dessin dessin, String nomFichier, Interpreteur interpreteur) throws IOException {
        File fichier = resoudre(nomFichier);
        if (!fichier.exists()) {
            System.out.println("Fichier introuvable : " + fichier.getPath());
            return;
        }

        Scanner reader = new Scanner(fichier);
        dessin.vider();

        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            if (line.contains("<forme")) {
                // Extraction du texte entre les balises XML
                String contenu = line.substring(line.indexOf(">") + 1, line.lastIndexOf("<"));
                String[] mots = contenu.split(" ");
                // On utilise la méthode public de l'interpréteur
                interpreteur.executer(mots[0], mots);
            }
        }
        reader.close();
    }
}