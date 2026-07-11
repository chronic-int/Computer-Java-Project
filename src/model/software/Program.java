package model.Software;

public class Program {
  private final String name;
  private String version;
  private boolean running;

  public Program() {
    this("Program", "1.0");
  }

  public Program(String name, String version) {
    this.name = name;
    this.version = version;
  }

  public void run() {
    running = true;
  }

  public void stop() {
    running = false;
  }

  public void update(String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public boolean isRunning() {
    return running;
  }
}
