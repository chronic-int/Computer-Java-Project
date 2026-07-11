package model.Software;

public class Browser extends Program {
  public Browser() {
    super("Browser", "1.0");
  }

  public String openUrl(String url) {
    run();
    return "Opened " + url;
  }
}
