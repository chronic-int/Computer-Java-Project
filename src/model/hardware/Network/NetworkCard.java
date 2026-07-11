package model.Hardware.Network;

public class NetworkCard {
  private final String macAddress;
  private final int speedMbps;

  public NetworkCard() {
    this("00:00:00:00:00:00", 1000);
  }

  public NetworkCard(String macAddress, int speedMbps) {
    this.macAddress = macAddress;
    this.speedMbps = speedMbps;
  }

  public String getMacAddress() {
    return macAddress;
  }

  public int getSpeedMbps() {
    return speedMbps;
  }
}
