package model.Hardware.Storage;

public class HDD {
  private final String brand;
  private final int capacityGb;
  private final int rpm;

  public HDD() {
    this("Generic", 1000, 7200);
  }

  public HDD(String brand, int capacityGb, int rpm) {
    this.brand = brand;
    this.capacityGb = capacityGb;
    this.rpm = rpm;
  }

  public String getDescription() {
    return brand + " HDD " + capacityGb + "GB " + rpm + "RPM";
  }
}
