package model.hardware.slots;

import exception.HardwareException;
import model.enums.TipoDDR;
import model.hardware.memoria.RAM;

public class SlotRAM {
  private final int numero;
  private final String canal;
  private final TipoDDR tipoDDR;
  private RAM modulo;

  public SlotRAM(int numero, String canal, TipoDDR tipoDDR) {
    this.numero = numero;
    this.canal = canal;
    this.tipoDDR = tipoDDR;
  }

  public void instalar(RAM modulo) {
    if (this.modulo != null) {
      throw new HardwareException("O slot RAM " + numero + " ja esta ocupado.");
    }
    if (modulo.getTipoDDR() != tipoDDR) {
      throw new HardwareException("Modulo RAM incompativel: slot e " + tipoDDR
          + ", modulo e " + modulo.getTipoDDR() + ".");
    }
    this.modulo = modulo;
  }

  public boolean estaOcupado() {
    return modulo != null;
  }

  public int getNumero() {
    return numero;
  }

  public String getCanal() {
    return canal;
  }

  public RAM getModulo() {
    return modulo;
  }
}
