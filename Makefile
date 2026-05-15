ifeq ($(OS),Windows_NT)
    MVNW = .\mvnw.cmd
else
    MVNW = ./mvnw
endif

compile:
	$(MVNW) compile

run:
	$(MVNW) exec:java

clean:
	$(MVNW) clean

build:
	$(MVNW) clean install

merge12:
	java -cp "target/classes" fr.univ_rouen.draw.io.Merge drawings/1.vec drawings/2.vec drawings/fusion12.vec

merge:
	java -cp "target/classes" fr.univ_rouen.draw.io.Merge $(ARGS)

image_test:
	java -cp "target/classes" fr.univ_rouen.draw.io.V2Bmp drawings/fusion12.vec drawings/image_finale.png

image:
	java -cp "target/classes" fr.univ_rouen.draw.io.V2Bmp $(ARGS)