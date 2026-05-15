# ProjetAL-2025

Éditeur de dessin vectoriel — projet M1 Architecture Logicielle 2025-2026.

---

## Prérequis

- Java 21
- Maven (ou utiliser `mvnw` / `mvnw.cmd` inclus dans le projet)
- VS Code : installer l'extension **Java Extension Pack**

---

## Compilation et lancement

### Nettoyer + compiler + packager
```bash
# Linux / macOS
./mvnw clean install

# Windows
.\mvnw.cmd clean install
```
> Compile les sources, lance les tests et génère le JAR dans `target/`.

### Compiler uniquement
```bash
./mvnw compile          # Linux/macOS
.\mvnw.cmd compile      # Windows
```

### Lancer l'éditeur interactif
```bash
./mvnw exec:java        # Linux/macOS
.\mvnw.cmd exec:java    # Windows
```
> Ouvre la fenêtre graphique et attend vos commandes dans le terminal.

### Avec Make (si GNU Make est installé)
```bash
make compile   # compile
make run       # lance l'éditeur
make clean     # nettoie le dossier target/
make build     # clean + install
```

---

## Commandes de l'éditeur

Une fois l'éditeur lancé, tapez vos commandes dans le terminal.
Les fichiers `.vec` sont automatiquement sauvegardés dans le dossier `drawings/`.

### Dessiner des formes

| Commande | Description | Exemple |
|----------|-------------|---------|
| `line x y x' y' col` | Trace une ligne entre (x,y) et (x',y') | `line 10 10 200 100 red` |
| `rect x y x' y' col` | Dessine un rectangle entre (x,y) et (x',y') | `rect 50 50 180 140 blue` |
| `circ x y r col` | Dessine un cercle de centre (x,y) et de rayon r | `circ 300 200 60 green` |
| `elli x y rx ry col` | Dessine une ellipse de centre (x,y), rayon horizontal rx, vertical ry | `elli 450 250 90 40 black` |

Couleurs disponibles : `red`, `blue`, `green`, `black`

### Gérer le dessin

| Commande | Description |
|----------|-------------|
| `list` | Liste toutes les formes numérotées |
| `new` | Efface le dessin en cours et en commence un nouveau |
| `save fichier.vec` | Sauvegarde le dessin dans `drawings/fichier.vec` |
| `load fichier.vec` | Charge un dessin depuis `drawings/fichier.vec` |
| `quit` | Quitte l'application |

### Groupes

| Commande | Description | Exemple |
|----------|-------------|---------|
| `grp r1,r2,... label` | Regroupe les formes aux rangs r1, r2... sous le nom `label` | `grp 1,3 mon groupe` |
| `ugrp r` | Défait le groupe au rang r (ses enfants reviennent à la racine) | `ugrp 2` |

> Le rang d'une forme s'obtient avec `list`.

---

## Programmes standalone

### Fusionner deux dessins (Merge)
```bash
./mvnw exec:java -Dexec.mainClass=fr.univ_rouen.draw.io.Merge \
  -Dexec.args="A.vec B.vec fusion.vec"
```
> Fusionne `A.vec` et `B.vec` en un seul fichier `fusion.vec`.

### Convertir un dessin en image PNG (V2Bmp)
```bash
./mvnw exec:java -Dexec.mainClass=fr.univ_rouen.draw.io.V2Bmp \
  -Dexec.args="drawings/mondessin.vec mondessin.png"
```
> Génère une image PNG 800×600 à partir d'un fichier `.vec`.

---

## Structure du projet

```
src/main/java/fr/univ_rouen/draw/
├── editor/
│   ├── AfficheurDessin.java   — panneau graphique Swing
│   ├── EditeurFrame.java      — fenêtre principale
│   └── Interpreteur.java      — interpréteur de commandes
├── io/
│   ├── GestionnaireFichier.java — save/load XML
│   ├── Merge.java               — fusion de fichiers .vec
│   └── V2Bmp.java               — conversion .vec → PNG
└── model/
    ├── Forme.java    — interface commune
    ├── Dessin.java   — liste de formes
    ├── line.java     — ligne
    ├── rect.java     — rectangle
    ├── circ.java     — cercle
    ├── elli.java     — ellipse
    └── Groupe.java   — groupe de formes
drawings/             — dossier des fichiers .vec sauvegardés
```

