package model.hardware.rede;

public class PlacaRede {
  private final String macAddress;
  private final int velocidadeMbps;

  public PlacaRede(String macAddress, int velocidadeMbps) {
    this.macAddress = macAddress;
    this.velocidadeMbps = velocidadeMbps;
  }

  public String getMacAddress() {
    return macAddress;
  }

  public int getVelocidadeMbps() {
    return velocidadeMbps;
  }
}
