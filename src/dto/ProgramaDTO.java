package dto;

public class ProgramaDTO {
  private final String nome;
  private final String versao;
  private final String estado;

  public ProgramaDTO(String nome, String versao, String estado) {
    this.nome = nome;
    this.versao = versao;
    this.estado = estado;
  }

  public String getNome() {
    return nome;
  }

  public String getVersao() {
    return versao;
  }

  public String getEstado() {
    return estado;
  }
}
