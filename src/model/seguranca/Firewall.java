package model.seguranca;

public class Firewall {
  private boolean ativo;

  public void ativar() {
    ativo = true;
  }

  public void desativar() {
    ativo = false;
  }

  public boolean isAtivo() {
    return ativo;
  }
}
