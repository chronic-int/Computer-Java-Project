package model.Hardware.Refrigerations;

public class waterCooler {
  private final int radiatorSizeMm;
  private final Fan fan;

  public waterCooler() {
    this(240, new Fan(120));
  }

  public waterCooler(int radiatorSizeMm, Fan fan) {
    this.radiatorSizeMm = radiatorSizeMm;
    this.fan = fan;
  }

  public String getDescription() {
    return "Water cooler " + radiatorSizeMm + "mm with " + fan.getDiameterMm() + "mm fan";
  }
}
