package model.sistema;

public class BIOS {
  private final String fabricante;
  private final String versao;

  public BIOS(String fabricante, String versao) {
    this.fabricante = fabricante;
    this.versao = versao;
  }

  public String getFabricante() {
    return fabricante;
  }

  public String getVersao() {
    return versao;
  }

  public String inicializar() {
    return "BIOS " + fabricante + " " + versao + " inicializada.";
  }
}
