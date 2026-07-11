package model.hardware.energia;

import model.hardware.ComponenteHardware;

public class Fonte extends ComponenteHardware {
  private final String marca;
  private final int potenciaWatts;
  private final String certificacao;
  private boolean caboEpsCpuLigado;
  private boolean caboPcieGpuLigado;

  public Fonte(String marca, int potenciaWatts, String certificacao) {
    super();
    this.marca = marca;
    this.potenciaWatts = potenciaWatts;
    this.certificacao = certificacao;
  }

  public String getDescricao() {
    return marca + " " + potenciaWatts + "W " + certificacao;
  }

  public int getPotenciaWatts() {
    return potenciaWatts;
  }

  public boolean isCaboEpsCpuLigado() {
    return caboEpsCpuLigado;
  }

  public void ligarCaboEpsCpu() {
    this.caboEpsCpuLigado = true;
  }

  public boolean isCaboPcieGpuLigado() {
    return caboPcieGpuLigado;
  }

  public void ligarCaboPcieGpu() {
    this.caboPcieGpuLigado = true;
  }
}
