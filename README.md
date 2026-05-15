# ProjetAL-2025

## run the project

to run the project u need to have

java 21
maven

for vscode u need
java extension pack

to build the app

mvn clean install

to compile the app

mvn compile

to run the app:

```bash
 ./mvnw exec:java -Dexec.mainClass=org.example.Main
```

some test commands:

```bash
line 10 10 200 100 red  (to draw a line)
rect 50 50 180 140 blue (to draw a rect)
circ 300 200 60 green (to draw a circle)
elli 450 250 90 40 black (to draw an elli)
list (to list all shapes)
save dessing.vec (to save the drawing)
new (to start a new drawing)
quit (to exit the application)
```
