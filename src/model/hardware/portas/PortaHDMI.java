package model.hardware.portas;

public class PortaHDMI {
  private boolean monitorConectado;

  public void conectarMonitor() {
    monitorConectado = true;
  }

  public boolean isMonitorConectado() {
    return monitorConectado;
  }
}
