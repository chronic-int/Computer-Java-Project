package model.hardware.portas;

public class PortaUSB {
  private final String versao;
  private boolean ocupada;

  public PortaUSB(String versao) {
    this.versao = versao;
  }

  public void conectar() {
    ocupada = true;
  }

  public void desconectar() {
    ocupada = false;
  }

  public String getVersao() {
    return versao;
  }

  public boolean isOcupada() {
    return ocupada;
  }
}
