package model.hardware.armazenamento;

public class HDD {
  private final String marca;
  private final int capacidadeGb;
  private final int rpm;
  private final int consumoWatts;

  public HDD(String marca, int capacidadeGb, int rpm) {
    this.marca = marca;
    this.capacidadeGb = capacidadeGb;
    this.rpm = rpm;
    this.consumoWatts = 9;
  }

  public String getDescricao() {
    return marca + " HDD " + capacidadeGb + "GB " + rpm + "RPM";
  }

  public int getConsumoWatts() {
    return consumoWatts;
  }
}
