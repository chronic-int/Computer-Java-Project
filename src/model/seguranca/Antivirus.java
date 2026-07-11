package model.seguranca;

public class Antivirus {
  private final String nome;
  private boolean protecaoAtiva;

  public Antivirus(String nome) {
    this.nome = nome;
  }

  public void ativarProtecao() {
    protecaoAtiva = true;
  }

  public String getNome() {
    return nome;
  }

  public boolean isProtecaoAtiva() {
    return protecaoAtiva;
  }
}
