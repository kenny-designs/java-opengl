JC = javac
JFLAGS = -cp ".:jars/*"
.SUFFIXES : .java .class
.java.class :
	$(JC) $(JFLAGS) $*.java

CLASSES = \
					Main.java \
					GameEngine.java \
					IGameLogic.java \
					Window.java \
					Timer.java \
					Teapot.java \
					Camera.java \
					Renderer.java \
					Transformation.java \
					GameItem.java \
					Mesh.java \
					Texture.java \
					ShaderProgram.java \
					Utils.java

default: classes

classes : $(CLASSES:.java=.class)

run : Main.class
	java $(JFLAGS) Main

clean :
	$(RM) *.class
