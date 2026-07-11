package model.hardware.slots;

import exception.HardwareException;
import model.hardware.grafico.GPU;

public class SlotPCIe {
  private final int numero;
  private GPU gpu;

  public SlotPCIe(int numero) {
    this.numero = numero;
  }

  public void instalar(GPU gpu) {
    if (this.gpu != null) {
      throw new HardwareException("O slot PCIe " + numero + " ja esta ocupado.");
    }
    this.gpu = gpu;
  }

  public boolean estaOcupado() {
    return gpu != null;
  }

  public GPU getGpu() {
    return gpu;
  }
}
