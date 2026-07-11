package model.System;

public class BIOS {
  private final String vendor;
  private final String version;

  public BIOS() {
    this("AMI", "1.0");
  }

  public BIOS(String vendor, String version) {
    this.vendor = vendor;
    this.version = version;
  }

  public String initialize() {
    return "BIOS " + vendor + " " + version + " initialized";
  }

  public String getVendor() {
    return vendor;
  }

  public String getVersion() {
    return version;
  }
}
