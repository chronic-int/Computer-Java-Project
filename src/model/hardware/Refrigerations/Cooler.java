package model.Hardware.Refrigerations;

public class Cooler {
  private final String model;
  private final Fan fan;

  public Cooler() {
    this("Air Cooler", new Fan());
  }

  public Cooler(String model, Fan fan) {
    this.model = model;
    this.fan = fan;
  }

  public String getDescription() {
    return model + " with " + fan.getDiameterMm() + "mm fan";
  }
}
