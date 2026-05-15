# TODO — ProjetAL-2025

## ✅ What's done well

- **Core editor commands** — `line`, `rect`, `circ`, `elli`, `list`, `new`, `save`, `load`, `quit` all implemented
- **Groups** — `grp` and `ugrp` working correctly (visible in the screenshot terminal session)
- **Visual rendering** — graphical window shows shapes in real time
- **4 colors** — `red`, `blue`, `green`, `black` (meets the ≥3 minimum)
- **File I/O** — `.vec` saving/loading with a `drawings/` folder convention
- **Standalone programs** — `Merge` and `V2Bmp` exist and are documented
- **Build tooling** — Maven wrapper + Makefile, well-documented in README

---

## ❌ Missing / Incomplete

### Functional gaps (spec requirements)

- [ ] **XML format** — Verify `.vec` files are valid XML dialect (not a custom text format). The terminal shows `circ 300 200 60 green` style output; confirm the saved file is XML, not plain text.
- [ ] **DTD or XSD** — The spec explicitly requires you to provide a DTD or XSD for your XML format. This must ship with the project.
- [ ] **`quit` command** — Not listed in the README command table. Verify it's implemented and document it.
- [ ] **Merge program** — Check that it handles groups correctly (nested elements in XML) and doesn't flatten structure.
- [ ] **V2Bmp program** — Verify ellipses and groups render correctly in the PNG output (groups must render all children recursively).

### Submission requirements (section 4)

- [ ] **PDF report** — Required, counts as much as the code. Must include:
  - [ ] Global architecture diagram (UML)
  - [ ] For each design pattern used: motivation, UML diagram with roles mapped to your classes
  - [ ] Patterns considered but rejected, with reasons
  - [ ] DTD/XSD for the `.vec` XML format
  - [ ] Review of adherence to architecture principles seen in class
- [ ] **Javadoc** — Must be generated (`mvn javadoc:javadoc`) and placed in a `javadoc/` subfolder in the archive
- [ ] **Archive naming** — Must be `projet-al-nom1-nom2.zip` containing a folder of the same name
- [ ] **Package list in appendix** — Report must include a full list of all classes/interfaces by package, each with a one-sentence responsibility

### Code quality (section 5)

- [ ] **Package architecture** — Justify the package split in the report (e.g. `model`, `io`, `ui`, `commands`)
- [ ] **Interface design** — Review and document your key interfaces in the report
- [ ] **Entity naming** — Check that class/interface names are semantically precise English (use a dictionary)
- [ ] **Design patterns** — Identify and document patterns in use. Likely candidates for this project:
  - _Composite_ — for the shape/group tree
  - _Command_ — for the editor interpreter
  - _Visitor_ — for rendering, serialization, merge
  - _Factory_ — for shape creation from commands or XML

---

## ⚠️ Things to double-check

- [ ] The `load` command path: README says files go in `drawings/`, but the terminal shows `Fichier introuvable : drawings/4` — make sure error messages are clear when a file is missing
- [ ] `ugrp` promotes children to root — confirm this works when the group is nested inside another group
- [ ] `grp` label with spaces (e.g. `grp 1,3 mon groupe`) — test that multi-word labels are parsed correctly
- [ ] PNG output dimensions — V2Bmp README says 800×600; make sure shapes with large coordinates aren't clipped


consider refactoring those to Line.java, Rect.java, Circ.java, and Elli.java to avoid losing easy points on basic Java convention