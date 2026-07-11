package model.hardware.refrigeracao;

public class Cooler {
  private final String modelo;
  private final Ventoinha ventoinha;

  public Cooler(String modelo, Ventoinha ventoinha) {
    this.modelo = modelo;
    this.ventoinha = ventoinha;
  }

  public String getDescricao() {
    return modelo + " com ventoinha de " + ventoinha.getDiametroMm() + "mm";
  }
}
