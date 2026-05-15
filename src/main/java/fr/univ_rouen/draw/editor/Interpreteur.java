package fr.univ_rouen.draw.editor;

import fr.univ_rouen.draw.model.*;
import java.util.Scanner;
import fr.univ_rouen.draw.io.GestionnaireFichier;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Interpreteur {
    private Dessin dessin;
    private EditeurFrame frame;

    public Interpreteur(Dessin dessin, EditeurFrame frame) {
        this.dessin = dessin;
        this.frame = frame;
    }

    public void lancer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Editeur prêt. Tapez vos commandes (ex: line 10 10 100 100 red) :");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String ligne = scanner.nextLine();
            if (ligne.isEmpty()) continue;

            String[] mots = ligne.split(" ");
            String commande = mots[0].toLowerCase();

            if (commande.equals("quit")) {
                System.out.println("Au revoir !");
                scanner.close();
                frame.dispose();
                System.exit(0);
            }

            executer(commande, mots);
            frame.repaint();
        }
        scanner.close();
    }

    // CHANGEMENT ICI : de private à public
    public void executer(String cmd, String[] args) {
        try {
            switch (cmd) {
                case "line":
                    dessin.ajouterForme(new line(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]));
                    break;
                case "rect":
                    dessin.ajouterForme(new rect(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]));
                    break;
                case "circ":
                    dessin.ajouterForme(new circ(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4]));
                    break;
                case "elli":
                    dessin.ajouterForme(new elli(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), args[5]));
                    break;
                case "new":
                    dessin.vider();
                    break;
                case "list":
                    List<Forme> fms = dessin.getFormes();
                    for (int i = 0; i < fms.size(); i++) {
                        System.out.println((i + 1) + " " + fms.get(i).toString());
                    }
                    break;
                case "save":
                    GestionnaireFichier.sauvegarder(dessin, "drawings/" + args[1]);
                    System.out.println("Dessin sauvegardé dans drawings/" + args[1]);
                    break;
                case "load": 
                    GestionnaireFichier.charger(dessin, "drawings/" + args[1], this);
                    System.out.println("Dessin chargé depuis drawings/" + args[1]);
                    break;
                case "grp":
                    try {
                        String[] indices = args[1].split(",");
                        String label = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                        Groupe nouveauGroupe = new Groupe(label);

                        // 1. On récupère les formes d'abord
                        List<Forme> aGrouper = new ArrayList<>();
                        for (String s : indices) {
                            int index = Integer.parseInt(s.trim()) - 1;
                            aGrouper.add(dessin.getFormes().get(index));
                        }

                        // 2. On les ajoute au groupe
                        for (Forme f : aGrouper) {
                            nouveauGroupe.ajouter(f);
                        }

                        // 3. On les SUPPRIME de la liste principale du dessin
                        for (Forme f : aGrouper) {
                            dessin.getFormes().remove(f);
                        }

                        // 4. On ajoute le groupe à la place
                        dessin.ajouterForme(nouveauGroupe);
                        System.out.println("Groupe '" + label + "' créé avec succès.");
                    } catch (Exception e) {
                        System.out.println("Erreur grp : vérifiez le format (ex: grp 1,2 label)");
                    }
                    break;
                case "ugrp":
                    try {
                        int rang = Integer.parseInt(args[1]) - 1;
                        Forme cible = dessin.getFormes().get(rang);
                        if (cible instanceof Groupe) {
                            Groupe g = (Groupe) cible;
                            dessin.getFormes().remove(rang);
                            for (Forme enfant : g.getEnfants()) {
                                dessin.ajouterForme(enfant);
                            }
                            System.out.println("Groupe défait avec succès.");
                        } else {
                            System.out.println("L'élément " + args[1] + " n'est pas un groupe.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur ugrp : vérifiez le rang (ex: ugrp 2)");
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erreur : Vérifiez les paramètres de la commande " + cmd);
        }
    }
}