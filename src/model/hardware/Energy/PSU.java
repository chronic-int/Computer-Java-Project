package model.Hardware.Energy;

public class PSU {
  private final String brand;
  private final int watts;

  public PSU() {
    this("Generic", 500);
  }

  public PSU(String brand, int watts) {
    this.brand = brand;
    this.watts = watts;
  }

  public boolean canSupply(int requiredWatts) {
    return watts >= requiredWatts;
  }

  public String getBrand() {
    return brand;
  }

  public int getWatts() {
    return watts;
  }
}
