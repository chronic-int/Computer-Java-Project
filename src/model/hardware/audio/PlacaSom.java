package model.hardware.audio;

public class PlacaSom {
  private final String modelo;

  public PlacaSom(String modelo) {
    this.modelo = modelo;
  }

  public String getDescricao() {
    return "Placa de som " + modelo;
  }
}
