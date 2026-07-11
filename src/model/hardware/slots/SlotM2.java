package model.hardware.slots;

import exception.HardwareException;
import model.hardware.armazenamento.SSD;

public class SlotM2 {
  private final int numero;
  private SSD ssd;

  public SlotM2(int numero) {
    this.numero = numero;
  }

  public void instalar(SSD ssd) {
    if (this.ssd != null) {
      throw new HardwareException("O slot M.2 " + numero + " ja esta ocupado.");
    }
    this.ssd = ssd;
  }

  public boolean estaOcupado() {
    return ssd != null;
  }

  public SSD getSsd() {
    return ssd;
  }
}
