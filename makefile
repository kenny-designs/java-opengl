JC = javac
JARS = -cp ".:jars/*"
TARGET_DIR = -d "./target"
.SUFFIXES : .java .class
.java.class :
	$(JC) $(JARS) $(TARGET_DIR) $*.java

CLASSES = \
					game/Main.java \
					engine/GameEngine.java \
					engine/IGameLogic.java \
					engine/Window.java \
				  engine/Timer.java \
					game/Teapot.java \
					engine/graphics/Camera.java \
					game/Renderer.java \
					engine/graphics/Transformation.java \
					engine/GameItem.java \
					engine/graphics/Mesh.java \
					engine/graphics/Texture.java \
					engine/graphics/ShaderProgram.java \
					engine/Utils.java

default: classes

classes : $(CLASSES:.java=.class)

run : $(CLASSES:.java=.class)
	java -cp .:target:jars/* game.Main

clean :
	find ./target -name "*.class" -type f -delete
