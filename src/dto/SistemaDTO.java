package dto;

public class SistemaDTO {
  private final String nome;
  private final String versao;
  private final int processosAtivos;

  public SistemaDTO(String nome, String versao, int processosAtivos) {
    this.nome = nome;
    this.versao = versao;
    this.processosAtivos = processosAtivos;
  }

  public String getNome() {
    return nome;
  }

  public String getVersao() {
    return versao;
  }

  public int getProcessosAtivos() {
    return processosAtivos;
  }
}
