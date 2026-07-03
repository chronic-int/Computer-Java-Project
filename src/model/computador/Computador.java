package model.computador;

public class Computador {

  // Identidade fisica do computador

  private final String marca;
  private final String modelo; // Os atributos serão definidos de forma permanente.

  // Construtor - demostra quando o computador nasce
  public Computador(String marca, String modelo) {
    this.marca = marca;
    this.modelo = modelo;
  }

  public String getMarca(String marca) {
    return marca;
  }

  public String getModelo(String modelo) {
    return modelo;
  }
}
