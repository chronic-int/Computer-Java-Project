package model.hardware.sensores;

public class SensorTemperatura {
  private double temperaturaCelsius;

  public void medir(double temperaturaCelsius) {
    this.temperaturaCelsius = temperaturaCelsius;
  }

  public double getTemperaturaCelsius() {
    return temperaturaCelsius;
  }
}
