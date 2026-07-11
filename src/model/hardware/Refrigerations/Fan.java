package model.Hardware.Refrigerations;

public class Fan {
  private final int diameterMm;
  private int rpm;

  public Fan() {
    this(120);
  }

  public Fan(int diameterMm) {
    this.diameterMm = diameterMm;
  }

  public void setRpm(int rpm) {
    this.rpm = rpm;
  }

  public int getDiameterMm() {
    return diameterMm;
  }

  public int getRpm() {
    return rpm;
  }
}
