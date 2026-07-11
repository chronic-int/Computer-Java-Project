package model.Software;

public class Game extends Program {
  public Game() {
    super("Game", "1.0");
  }

  public String startMatch() {
    run();
    return "Match started";
  }
}
