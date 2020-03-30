import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements IGameLogic {
  private static final float MOUSE_SENSITIVITY = 0.2f,
                             CAMERA_POS_STEP = 0.05f;
  private final Vector3f cameraInc;
  private final Renderer renderer;
  private final Camera camera;
  private GameItem[] gameItems;

  public DummyGame() {
    renderer  = new Renderer();
    camera    = new Camera(new Vector3f(0, 0, 1.0f), new Vector3f());
    cameraInc = new Vector3f();
  }

  @Override
  public void init(Window window) throws Exception {
    renderer.init(window);

    // Create the Mesh
    float[] positions = new float[] {
      -0.5f,  0.5f,  0.0f, // V0
      -0.5f, -0.5f,  0.0f, // V1
       0.5f, -0.5f,  0.0f, // V2
       0.5f,  0.5f,  0.0f, // V3
      -0.5f,  0.5f,  0.0f, // V4
       0.5f,  0.5f,  0.0f, // V5
      -0.5f, -0.5f,  0.0f, // V6
       0.5f, -0.5f,  0.0f  // V7
    };
    float[] textCoords = new float[] {
      0.0f, 0.0f,
      0.0f, 1.0f,
      1.0f, 1.0f,
      1.0f, 0.0f,
    };
    int[] indices = new int[] {
      0, 1, 3, 3, 1, 2 // Front face
    };

    Texture texture = new Texture("teapot.png");
    Mesh mesh = new Mesh(positions, textCoords, indices, texture);

    GameItem gameItem = new GameItem(mesh);
    gameItem.setPosition(0, 0, 0);
    gameItems = new GameItem[] { gameItem };
  }

  @Override
  public void input(Window window) {
    cameraInc.set(0, 0, 0);
    if (window.isKeyPressed(GLFW_KEY_W)) {
      cameraInc.z = -1;
    }
    else if (window.isKeyPressed(GLFW_KEY_S)) {
      cameraInc.z = 1;
    }

    if (window.isKeyPressed(GLFW_KEY_A)) {
      cameraInc.x = -1;
    }
    else if (window.isKeyPressed(GLFW_KEY_D)) {
      cameraInc.x = 1;
    }

    if (window.isKeyPressed(GLFW_KEY_Z)) {
      cameraInc.y = -1;
    }
    else if (window.isKeyPressed(GLFW_KEY_X)) {
      cameraInc.y = 1;
    }

    // Change color
    if (window.isKeyPressed(GLFW_KEY_R)) {
      renderer.setFragmentColor(new Vector4f(1f, 0, 0, 0));
    }
    else if (window.isKeyPressed(GLFW_KEY_G)) {
      renderer.setFragmentColor(new Vector4f(0, 1f, 0, 0));
    }
    else if (window.isKeyPressed(GLFW_KEY_B)) {
      renderer.setFragmentColor(new Vector4f(0, 0, 1f, 0));
    }
  }

  @Override
  public void update(float interval) {
    // Update camera position
    camera.movePosition(
        cameraInc.x * CAMERA_POS_STEP,
        cameraInc.y * CAMERA_POS_STEP,
        cameraInc.z * CAMERA_POS_STEP);
  }

  @Override
  public void render(Window window) {
    renderer.render(window, camera, gameItems);
  }

  @Override
  public void cleanup() {
    renderer.cleanup();
    for (GameItem gameItem : gameItems) {
      gameItem.getMesh().cleanUp();
    }
  }
}
