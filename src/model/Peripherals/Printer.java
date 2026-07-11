package model.Peripherals;

public class Printer {
  private final String model;

  public Printer() {
    this("Generic Printer");
  }

  public Printer(String model) {
    this.model = model;
  }

  public String print(String documentName) {
    return model + " printed " + documentName;
  }
}
