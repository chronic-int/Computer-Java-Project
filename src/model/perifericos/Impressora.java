package model.perifericos;

public class Impressora {
  private final String modelo;

  public Impressora(String modelo) {
    this.modelo = modelo;
  }

  public String getDescricao() {
    return "Impressora " + modelo;
  }
}
