package engine;

public class GameEngine implements Runnable {
  public static final int TARGET_FPS = 60,
                          TARGET_UPS = 30;

  private final Window window;
  private final Timer timer;
  private final IGameLogic gameLogic;

  public GameEngine(String windowTitle,
      int width,
      int height,
      boolean vSync,
      IGameLogic gameLogic) throws Exception {
    window         = new Window(windowTitle, width, height, vSync);
    this.gameLogic = gameLogic;
    timer          = new Timer();
  }

  @Override
  public void run() {
    try {
      init();
      gameLoop();
    }
    catch (Exception excp) {
      excp.printStackTrace();
    }
    finally {
      cleanup();
    }
  }

  protected void init() throws Exception {
    window.init();
    timer.init();
    gameLogic.init(window);
  }

  protected void gameLoop() {
    float elapsedTime,
          accumulator = 0f,
          interval = 1f / TARGET_UPS;

    boolean running = true;
    while (running && !window.windowShouldClose()) {
      elapsedTime = timer.getElapsedTime();
      accumulator += elapsedTime;

      input();

      while (accumulator >= interval) {
        update(interval);
        accumulator -= interval;
      }

      render();

      if (!window.isvSync()) {
        sync();
      }
    }
  }

  protected void cleanup() {
    gameLogic.cleanup();
  }

  private void sync() {
    float loopSlot = 1f / TARGET_FPS;
    double endTime = timer.getLastLoopTime() + loopSlot;
    while (timer.getTime() < endTime) {
      try {
        Thread.sleep(1);
      }
      catch (InterruptedException ie) {
        // nothing to see here...
      }
    }
  }

  protected void input() {
    gameLogic.input(window);
  }

  protected void update(float interval) {
    gameLogic.update(interval);
  }

  protected void render() {
    gameLogic.render(window);
    window.update();
  }
}
