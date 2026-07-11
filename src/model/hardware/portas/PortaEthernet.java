package model.hardware.portas;

public class PortaEthernet {
  private boolean caboConectado;

  public void conectarCabo() {
    caboConectado = true;
  }

  public void desconectarCabo() {
    caboConectado = false;
  }

  public boolean isCaboConectado() {
    return caboConectado;
  }
}
