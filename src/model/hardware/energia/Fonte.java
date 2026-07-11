package model.hardware.energia;

public class Fonte {
  private final String marca;
  private final int potenciaWatts;
  private final String certificacao;

  public Fonte(String marca, int potenciaWatts, String certificacao) {
    this.marca = marca;
    this.potenciaWatts = potenciaWatts;
    this.certificacao = certificacao;
  }

  public String getDescricao() {
    return marca + " " + potenciaWatts + "W " + certificacao;
  }

  public int getPotenciaWatts() {
    return potenciaWatts;
  }
}
