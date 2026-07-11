package model.Peripherals;

public class Webacam {
  private final String resolution;

  public Webacam() {
    this("1080p");
  }

  public Webacam(String resolution) {
    this.resolution = resolution;
  }

  public String captureFrame() {
    return "Frame captured at " + resolution;
  }
}
