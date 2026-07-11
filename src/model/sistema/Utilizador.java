package model.sistema;

public class Utilizador {
  private final String nome;
  private final boolean administrador;

  public Utilizador(String nome, boolean administrador) {
    this.nome = nome;
    this.administrador = administrador;
  }

  public String getNome() {
    return nome;
  }

  public boolean isAdministrador() {
    return administrador;
  }
}
