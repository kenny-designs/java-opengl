package engine;

import org.joml.Vector3f;
import engine.graphics.Mesh;

public class GameItem {
  private final Mesh mesh; 
  private final Vector3f position,
                         rotation;
  private float scale;

  public GameItem(Mesh mesh) {
    this.mesh = mesh;
    position  = new Vector3f();
    rotation  = new Vector3f();
    scale     = 1;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(float x, float y, float z) {
    this.position.x = x;
    this.position.y = y;
    this.position.z = z;
  }

  public float getScale() {
    return scale;
  }

  public void setScale(float scale) {
    this.scale = scale;
  }

  public Vector3f getRotation() {
    return rotation;
  }

  public void setRotation(float x, float y, float z) {
    this.rotation.x = x;
    this.rotation.y = y;
    this.rotation.z = z;
  }

  public Mesh getMesh() {
    return mesh;
  }
}
