package fr.univ_rouen.draw.io;

import fr.univ_rouen.draw.model.Dessin;
import fr.univ_rouen.draw.model.Forme;
import fr.univ_rouen.draw.editor.Interpreteur;
import java.util.ArrayList;
import java.util.List;

public class Merge {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Merge <fich1.vec> <fich2.vec> ... <resultat.vec>");
            return;
        }

        Dessin dessinFinal = new Dessin();
        // On a besoin d'un interpréteur "fantôme" (sans fenêtre) pour charger les formes
        Interpreteur interpreteurMuet = new Interpreteur(dessinFinal, null);

        try {
            // On boucle sur tous les fichiers sauf le dernier (qui est la destination)
            for (int i = 0; i < args.length - 1; i++) {
                System.out.println("Fusion de : " + args[i]);

                // On charge les formes du fichier dans une liste temporaire
                Dessin dessinTemporaire = new Dessin();
                GestionnaireFichier.charger(dessinTemporaire, args[i], new Interpreteur(dessinTemporaire, null));

                // On ajoute ces formes au dessin final
                for (Forme f : dessinTemporaire.getFormes()) {
                    dessinFinal.ajouterForme(f);
                }
            }

            // On sauvegarde le tout dans le dernier fichier mentionné
            String nomResultat = args[args.length - 1];
            GestionnaireFichier.sauvegarder(dessinFinal, nomResultat);
            System.out.println("Fusion terminée avec succès dans : " + nomResultat);

        } catch (Exception e) {
            System.err.println("Erreur durant la fusion : " + e.getMessage());
        }
    }
}