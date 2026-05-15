package fr.univ_rouen.draw.editor;

import fr.univ_rouen.draw.model.Dessin;
import fr.univ_rouen.draw.model.Forme;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;

public class AfficheurDessin extends JPanel {
    private Dessin dessin;

    public AfficheurDessin(Dessin dessin) {
        this.dessin = dessin;
        this.setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Forme f : dessin.getFormes()) {
            f.dessiner(g);
        }
    }
}