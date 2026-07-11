package dto;

public class HardwareDTO {
  private final String nome;
  private final String estado;

  public HardwareDTO(String nome, String estado) {
    this.nome = nome;
    this.estado = estado;
  }

  public String getNome() {
    return nome;
  }

  public String getEstado() {
    return estado;
  }
}
