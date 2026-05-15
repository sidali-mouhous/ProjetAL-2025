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
