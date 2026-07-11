package model.Hardware.Memory;

public class RAM {
  private final String brand;
  private final String model;
  private final int capacityGb;
  private final String ddrType;

  public RAM() {
    this("Generic", "Memory Module", 8, "DDR4");
  }

  public RAM(String brand, String model, int capacityGb, String ddrType) {
    this.brand = brand;
    this.model = model;
    this.capacityGb = capacityGb;
    this.ddrType = ddrType;
  }

  public String getDescription() {
    return brand + " " + model + " " + capacityGb + "GB " + ddrType;
  }

  public int getCapacityGb() {
    return capacityGb;
  }
}
