package model.hardware.gabinete;

import model.enums.TipoMotherboard;

public class Gabinete {
  private final String modelo;
  private final TipoMotherboard maiorFormatoSuportado;
  private boolean espacadoresInstalados;

  public Gabinete(String modelo, TipoMotherboard maiorFormatoSuportado) {
    this.modelo = modelo;
    this.maiorFormatoSuportado = maiorFormatoSuportado;
  }

  public String getModelo() {
    return modelo;
  }

  public boolean suporta(TipoMotherboard tipoMotherboard) {
    return maiorFormatoSuportado.ordinal() <= tipoMotherboard.ordinal();
  }

  public boolean isEspacadoresInstalados() {
    return espacadoresInstalados;
  }

  public void instalarEspacadores() {
    this.espacadoresInstalados = true;
  }
}
