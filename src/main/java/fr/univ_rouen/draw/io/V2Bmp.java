package fr.univ_rouen.draw.io;

import fr.univ_rouen.draw.model.Dessin;
import fr.univ_rouen.draw.model.Forme;
import fr.univ_rouen.draw.editor.Interpreteur;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class V2Bmp {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java V2Bmp <source.vec> <destination.png>");
            return;
        }

        try {
            String source = args[0];
            String destination = args[1];

            // 1. Charger le dessin
            Dessin dessin = new Dessin();
            GestionnaireFichier.charger(dessin, source, new Interpreteur(dessin, null));

            // 2. Créer l'image en mémoire (800x600 par défaut)
            BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            // 3. Fond blanc (sinon c'est noir par défaut)
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, 800, 600);

            // 4. Dessiner chaque forme sur l'image
            for (Forme f : dessin.getFormes()) {
                f.dessiner(g2d);
            }

            g2d.dispose();

            // 5. Sauvegarder le fichier
            File output = new File(destination);
            ImageIO.write(image, "png", output);

            System.out.println("Image générée avec succès : " + destination);

        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion : " + e.getMessage());
        }
    }
}