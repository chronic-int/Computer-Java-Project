package model.ficheiros;

public class Ficheiro {
  private final String nome;
  private String conteudo;

  public Ficheiro(String nome, String conteudo) {
    this.nome = nome;
    this.conteudo = conteudo;
  }

  public String getNome() {
    return nome;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void escrever(String conteudo) {
    this.conteudo = conteudo;
  }
}
