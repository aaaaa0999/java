all: run

clean:
	rm -f out/Determinant.jar

out/Determinant.jar: out/parcs.jar src/Main.java src/Determinant.java src/Matrix.java
	@javac -cp out/parcs.jar src/Main.java src/Determinant.java src/Matrix.java
	@jar cf out/Determinant.jar -C src Main.class -C src Determinant.class -C src Matrix.class
	@rm -f src/Main.class src/Determinant.class src/Matrix.class

build: out/Determinant.jar

run: out/Determinant.jar
	@cd out && java -cp 'parcs.jar:Determinant.jar' Main


