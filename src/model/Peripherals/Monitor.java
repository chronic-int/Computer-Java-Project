package model.Peripherals;

public class Monitor {
  private final String resolution;
  private boolean poweredOn;

  public Monitor() {
    this("1920x1080");
  }

  public Monitor(String resolution) {
    this.resolution = resolution;
  }

  public void powerOn() {
    poweredOn = true;
  }

  public String getResolution() {
    return resolution;
  }

  public boolean isPoweredOn() {
    return poweredOn;
  }
}
