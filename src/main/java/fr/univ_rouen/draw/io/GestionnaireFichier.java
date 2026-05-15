package fr.univ_rouen.draw.io;

import fr.univ_rouen.draw.model.*;
import fr.univ_rouen.draw.editor.Interpreteur;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class GestionnaireFichier {

    public static void sauvegarder(Dessin dessin, String nomFichier) throws IOException {
        PrintWriter writer = new PrintWriter(nomFichier);
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
        File fichier = new File(nomFichier);
        if (!fichier.exists()) return;

        Scanner reader = new Scanner(fichier);
        dessin.vider();

        while (reader.hasNextLine()) {
            String ligne = reader.nextLine().trim();
            if (ligne.contains("<forme")) {
                // Extraction du texte entre les balises XML
                String contenu = ligne.substring(ligne.indexOf(">") + 1, ligne.lastIndexOf("<"));
                String[] mots = contenu.split(" ");
                // On utilise la méthode public de l'interpréteur
                interpreteur.executer(mots[0], mots);
            }
        }
        reader.close();
    }
}