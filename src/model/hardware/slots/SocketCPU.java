package model.hardware.slots;

import exception.HardwareException;
import model.enums.TipoSocket;
import model.hardware.processamento.CPU;

public class SocketCPU {
  private final TipoSocket tipoSocket;
  private CPU cpu;

  public SocketCPU(TipoSocket tipoSocket) {
    this.tipoSocket = tipoSocket;
  }

  public void instalar(CPU cpu) {
    if (this.cpu != null) {
      throw new HardwareException("O socket da CPU ja esta ocupado.");
    }
    if (cpu.getSocket() != tipoSocket) {
      throw new HardwareException("CPU incompativel: socket da motherboard e " + tipoSocket
          + ", mas a CPU usa " + cpu.getSocket() + ".");
    }
    this.cpu = cpu;
  }

  public boolean estaOcupado() {
    return cpu != null;
  }

  public CPU getCpu() {
    return cpu;
  }

  public TipoSocket getTipoSocket() {
    return tipoSocket;
  }
}
