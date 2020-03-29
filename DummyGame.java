import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;

public class DummyGame implements IGameLogic {
  private static final float MOUSE_SENSITIVITY = 0.2f;
  private final Vector3f cameraInc;
  private final Renderer renderer;
  private final Camera camera;
  private GameItem[] gameItems;
  private static final float CAMERA_POS_STEP = 0.05f;

  public DummyGame() {
    renderer  = new Renderer();
    camera    = new Camera();
    cameraInc = new Vector3f();
  }

  @Override
  public void init(Window window) throws Exception {
    renderer.init(window);

    // Create the Mesh
    float[] positions = new float[] {
      -0.5f,  0.5f,  0.5f, // V0
      -0.5f, -0.5f,  0.5f, // V1
       0.5f, -0.5f,  0.5f, // V2
       0.5f,  0.5f,  0.5f, // V3
      -0.5f,  0.5f, -0.5f, // V4
       0.5f,  0.5f, -0.5f, // V5
      -0.5f, -0.5f, -0.5f, // V6
       0.5f, -0.5f, -0.5f, // V7
      // For text coords in top face
      -0.5f,  0.5f, -0.5f, // V8: V4 repeated
       0.5f,  0.5f, -0.5f, // V9: V5 repeated
      -0.5f,  0.5f,  0.5f, // V10: V0 repeated
       0.5f,  0.5f,  0.5f, // V11: V3 repeated
      // For text coords in right face
       0.5f,  0.5f,  0.5f, // V12: V3 repeated
       0.5f, -0.5f,  0.5f, // V13: V2 repeated
      // For text coords in left face
      -0.5f,  0.5f,  0.5f, // V14: V0 repeated
      -0.5f, -0.5f,  0.5f, // V15: V1 repeated
      // For text coords in bottom face
      -0.5f, -0.5f, -0.5f, // V16: V6 repeated
       0.5f, -0.5f, -0.5f, // V17: V7 repeated
      -0.5f, -0.5f,  0.5f, // V18: V1 repeated
       0.5f, -0.5f,  0.5f  // V19: V2 repeated
    };
    float[] textCoords = new float[] {
      0.0f, 0.0f,
      0.0f, 0.5f,
      0.5f, 0.5f,
      0.5f, 0.0f,
      0.0f, 0.0f,
      0.5f, 0.0f,
      0.0f, 0.5f,
      0.5f, 0.5f,
      // For text coords in top face
      0.0f, 0.5f,
      0.5f, 0.5f,
      0.0f, 1.0f,
      0.5f, 1.0f,
      // For text coords in right face
      0.0f, 0.0f,
      0.0f, 0.5f,
      // For text coords in left face
      0.5f, 0.0f,
      0.5f, 0.5f,
      // For text coords in bottom face
      0.5f, 0.0f,
      1.0f, 0.0f,
      0.5f, 0.5f,
      1.0f, 0.5f
    };
    int[] indices = new int[] {
      0, 1, 3, 3, 1, 2,       // Front face
      8, 10, 11, 9, 8, 11,    // Top Face
      12, 13, 7, 5, 12, 7,    // Right face
      14, 15, 6, 4, 14, 6,    // Left face
      16, 18, 19, 17, 16, 19, // Bottom face
      4, 6, 7, 5, 4, 7        // Back face
    };

    Texture texture = new Texture("grassblock.png");
    Mesh mesh = new Mesh(positions, textCoords, indices, texture);

    //gameItems = new GameItem[9];
    Vector3f[] cubePositions = {
      // layer 0
      new Vector3f(0, 0, 0),
      new Vector3f(1, 0, 0),
      new Vector3f(2, 0, 0),
      new Vector3f(0, 0, 1),
      new Vector3f(1, 0, 1),
      new Vector3f(2, 0, 1),
      new Vector3f(0, 0, 2),
      new Vector3f(1, 0, 2),
      new Vector3f(2, 0, 2),
      // layer 1
      new Vector3f(1, 1, 0),
      new Vector3f(2, 1, 0),
      new Vector3f(1, 1, 1),
      new Vector3f(2, 1, 1),
      new Vector3f(2, 1, 2),
      // layer 2
      new Vector3f(1, 2, 0),
      new Vector3f(2, 2, 0)
    };

    createCubes(cubePositions, mesh);

      /*
    GameItem gameItem1 = new GameItem(mesh);
    gameItem1.setScale(0.5f);
    gameItem1.setPosition(0, 0, -2);
    GameItem gameItem2 = new GameItem(mesh);
    gameItem2.setScale(0.5f);
    gameItem2.setPosition(0.5f, 0.5f, -2);
    GameItem gameItem3 = new GameItem(mesh);
    gameItem3.setScale(0.5f);
    gameItem3.setPosition(0, 0, -2.5f);
    GameItem gameItem4 = new GameItem(mesh);
    gameItem4.setScale(0.5f);
    gameItem4.setPosition(0.5f, 0, -2.5f);
    gameItems = new GameItem[] { gameItem1, gameItem2, gameItem3, gameItem4 };
    */
  }

  public void createCubes(Vector3f[] positions, Mesh cubeMesh) {
    gameItems = new GameItem[positions.length];
    int i = 0;
    for (Vector3f pos : positions) {
      gameItems[i] = new GameItem(cubeMesh);
      gameItems[i].setPosition(pos.x, pos.y, pos.z);
      i++;
    }
  }

  @Override
  public void input(Window window, MouseInput mouseInput) {
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
  }

  @Override
  public void update(float interval, MouseInput mouseInput) {
    // Update camera position
    camera.movePosition(
        cameraInc.x * CAMERA_POS_STEP,
        cameraInc.y * CAMERA_POS_STEP,
        cameraInc.z * CAMERA_POS_STEP);

    // Update camera based on mouse
    if (mouseInput.isRightButtonPressed()) {
      Vector2f rotVec = mouseInput.getDisplVec();
      camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
    }
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
