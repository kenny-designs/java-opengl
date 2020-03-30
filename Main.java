public class Main {
  public static void main(String[] args) {
    try {
      boolean vSync = true;
      IGameLogic gameLogic = new Teapot();
      GameEngine gameEng = new GameEngine("Teapot", 600, 480, vSync, gameLogic);
      gameEng.run();
    }
    catch (Exception excp) {
      excp.printStackTrace();
      System.exit(-1);
    }
  }
}

