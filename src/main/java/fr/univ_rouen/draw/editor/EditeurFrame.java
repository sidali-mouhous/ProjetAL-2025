package fr.univ_rouen.draw.editor;

import fr.univ_rouen.draw.model.Dessin;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class EditeurFrame extends JFrame {
    private Dessin dessin;
    private AfficheurDessin afficheur;

    public EditeurFrame(Dessin dessin) {
        this.dessin = dessin;
        this.afficheur = new AfficheurDessin(dessin);

        this.setTitle("Draw GIL 2025 - " + System.getProperty("user.name"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(afficheur, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}