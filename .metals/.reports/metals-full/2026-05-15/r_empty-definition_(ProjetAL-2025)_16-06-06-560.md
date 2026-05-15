error id: file://<WORKSPACE>/src/main/java/fr/univ_rouen/draw/editor/EditeurFrame.java:java/lang/System#
file://<WORKSPACE>/src/main/java/fr/univ_rouen/draw/editor/EditeurFrame.java
empty definition using pc, found symbol in pc: java/lang/System#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 388
uri: file://<WORKSPACE>/src/main/java/fr/univ_rouen/draw/editor/EditeurFrame.java
text:
```scala
package fr.univ_rouen.draw.editor;

import fr.univ_rouen.draw.model.Dessin;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class EditeurFrame extends JFrame {
    private AfficheurDessin afficheur;

    public EditeurFrame(Dessin dessin) {
        this.dessin = dessin;
        this.afficheur = new AfficheurDessin(dessin);

        this.setTitle("Draw GIL 2025 - " + Sy@@stem.getProperty("user.name"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(afficheur, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/System#