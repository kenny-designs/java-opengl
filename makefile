Main.class : Main.java
	javac -cp ".:jars/*" Main.java

GameEngine.class : GameEngine.java
	javac -cp ".:jars/*" GameEngine.java

IGameLogic.class : IGameLogic.java
	javac -cp ".:jars/*" IGameLogic.java

Window.class : Window.java
	javac -cp ".:jars/*" Window.java

Timer.class : Timer.java
	javac -cp ".:jars/*" Timer.java

DummyGame.class : DummyGame.java
	javac -cp ".:jars/*" DummyGame.java

Camera.class : Camera.java
	javac -cp ".:jars/*" Camera.java

Renderer.class : Renderer.java
	javac -cp ".:jars/*" Renderer.java

Transformation.class : Transformation.java
	javac -cp ".:jars/*" Transformation.java

GameItem.class : GameItem.java
	javac -cp ".:jars/*" GameItem.java

Mesh.class : Mesh.java
	javac -cp ".:jars/*" Mesh.java

Texture.class : Texture.java
	javac -cp ".:jars/*" Texture.java

ShaderProgram.class : ShaderProgram.java
	javac -cp ".:jars/*" ShaderProgram.java

Utils.class : Utils.java
	javac -cp ".:jars/*" Utils.java

run : Main.class GameEngine.class IGameLogic.class Window.class Timer.class DummyGame.class Camera.class Renderer.class Transformation.class GameItem.class Mesh.class Texture.class ShaderProgram.class Utils.class
	java -cp ".:jars/*" Main

clean :
	rm *.class
