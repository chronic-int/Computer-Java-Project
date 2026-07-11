package model.hardware.sensores;

public class SensorEnergia {
  private int consumoAtualWatts;

  public void medir(int consumoAtualWatts) {
    this.consumoAtualWatts = consumoAtualWatts;
  }

  public int getConsumoAtualWatts() {
    return consumoAtualWatts;
  }
}
