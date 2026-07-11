package model.hardware.grafico;

import model.hardware.ComponenteHardware;

public class GPU extends ComponenteHardware {
  private final String marca;
  private final String modelo;
  private final int memoriaGb;
  private final int consumoWatts;

  public GPU(String marca, String modelo, int memoriaGb, int consumoWatts) {
    super();
    this.marca = marca;
    this.modelo = modelo;
    this.memoriaGb = memoriaGb;
    this.consumoWatts = consumoWatts;
  }

  public String getDescricao() {
    return marca + " " + modelo + " " + memoriaGb + "GB";
  }

  public int getConsumoWatts() {
    return consumoWatts;
  }
}
