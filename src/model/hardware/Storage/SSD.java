package model.Hardware.Storage;

public class SSD {
  private final String brand;
  private final int capacityGb;
  private final boolean bootable;

  public SSD() {
    this("Generic", 512, false);
  }

  public SSD(String brand, int capacityGb, boolean bootable) {
    this.brand = brand;
    this.capacityGb = capacityGb;
    this.bootable = bootable;
  }

  public boolean isBootable() {
    return bootable;
  }

  public String getDescription() {
    return brand + " SSD " + capacityGb + "GB";
  }
}
