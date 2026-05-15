package fr.univ_rouen.draw.io;

import fr.univ_rouen.draw.model.Dessin;
import fr.univ_rouen.draw.model.Forme;
import fr.univ_rouen.draw.editor.Interpreteur;

public class Merge {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Merge <fich1.vec> <fich2.vec> ... <resultat.vec>");
            return;
        }

        Dessin dessinFinal = new Dessin();

        try {
            for (int i = 0; i < args.length - 1; i++) {
                System.out.println("Fusion de : " + args[i]);

                Dessin dessinTemporaire = new Dessin();
                GestionnaireFichier.charger(dessinTemporaire, args[i], new Interpreteur(dessinTemporaire, null));

                for (Forme f : dessinTemporaire.getFormes()) {
                    dessinFinal.ajouterForme(f);
                }
            }

            String nomResultat = args[args.length - 1];
            GestionnaireFichier.sauvegarder(dessinFinal, nomResultat);
            System.out.println("Fusion terminée avec succès dans : " + nomResultat);

        } catch (Exception e) {
            System.err.println("Erreur durant la fusion : " + e.getMessage());
        }
    }
}