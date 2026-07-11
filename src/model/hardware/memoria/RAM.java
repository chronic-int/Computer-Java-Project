package model.hardware.memoria;

import model.enums.TipoDDR;

public class RAM {
  private final String marca;
  private final int capacidadeGb;
  private final TipoDDR tipoDDR;
  private final int frequenciaMhz;
  private final int consumoWatts;

  public RAM(String marca, int capacidadeGb, TipoDDR tipoDDR, int frequenciaMhz) {
    this.marca = marca;
    this.capacidadeGb = capacidadeGb;
    this.tipoDDR = tipoDDR;
    this.frequenciaMhz = frequenciaMhz;
    this.consumoWatts = 5;
  }

  public String getDescricao() {
    return marca + " " + capacidadeGb + "GB " + tipoDDR + " " + frequenciaMhz + "MHz";
  }

  public int getCapacidadeGb() {
    return capacidadeGb;
  }

  public TipoDDR getTipoDDR() {
    return tipoDDR;
  }

  public int getConsumoWatts() {
    return consumoWatts;
  }
}
