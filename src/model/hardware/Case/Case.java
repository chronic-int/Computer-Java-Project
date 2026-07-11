package model.Hardware.Case;

public class Case {
  private final String model;
  private final String supportedFormFactor;

  public Case() {
    this("Generic Case", "ATX");
  }

  public Case(String model, String supportedFormFactor) {
    this.model = model;
    this.supportedFormFactor = supportedFormFactor;
  }

  public String getModel() {
    return model;
  }

  public String getSupportedFormFactor() {
    return supportedFormFactor;
  }
}
