package dto;

public class ComputadorDTO {
  private final String marca;
  private final String modelo;
  private final String estado;

  public ComputadorDTO(String marca, String modelo, String estado) {
    this.marca = marca;
    this.modelo = modelo;
    this.estado = estado;
  }

  public String getMarca() {
    return marca;
  }

  public String getModelo() {
    return modelo;
  }

  public String getEstado() {
    return estado;
  }
}
