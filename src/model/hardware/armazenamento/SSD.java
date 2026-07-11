package model.hardware.armazenamento;

import model.enums.SistemaOperativoTipo;
import model.sistema.SistemaOperativo;

public class SSD {
  private final String marca;
  private final String modelo;
  private final int capacidadeGb;
  private final boolean contemSistemaOperativo;
  private final SistemaOperativo sistemaOperativo;
  private final int consumoWatts;

  public SSD(String marca, String modelo, int capacidadeGb, boolean contemSistemaOperativo) {
    this.marca = marca;
    this.modelo = modelo;
    this.capacidadeGb = capacidadeGb;
    this.contemSistemaOperativo = contemSistemaOperativo;
    this.sistemaOperativo = contemSistemaOperativo
        ? new SistemaOperativo("JavaOS", "1.0", SistemaOperativoTipo.LINUX)
        : null;
    this.consumoWatts = 6;
  }

  public SSD(String marca, String modelo, int capacidadeGb, SistemaOperativoTipo tipo) {
    this.marca = marca;
    this.modelo = modelo;
    this.capacidadeGb = capacidadeGb;
    this.contemSistemaOperativo = tipo != SistemaOperativoTipo.SEM_SISTEMA;
    this.sistemaOperativo = criarSistemaOperativo(tipo);
    this.consumoWatts = 6;
  }

  private SistemaOperativo criarSistemaOperativo(SistemaOperativoTipo tipo) {
    switch (tipo) {
      case WINDOWS:
        return new SistemaOperativo("Windows", "11 Pro", tipo);
      case MACOS:
        return new SistemaOperativo("macOS", "Sequoia", tipo);
      case LINUX:
        return new SistemaOperativo("Linux", "Ubuntu 24.04 LTS", tipo);
      case SEM_SISTEMA:
      default:
        return null;
    }
  }

  public String getDescricao() {
    return marca + " " + modelo + " " + capacidadeGb + "GB";
  }

  public boolean contemSistemaOperativo() {
    return contemSistemaOperativo;
  }

  public SistemaOperativo getSistemaOperativo() {
    return sistemaOperativo;
  }

  public int getConsumoWatts() {
    return consumoWatts;
  }
}
