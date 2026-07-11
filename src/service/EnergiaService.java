package service;

import exception.HardwareException;
import model.computador.Computador;

public class EnergiaService {
  private final HardwareService hardwareService;

  public EnergiaService(HardwareService hardwareService) {
    this.hardwareService = hardwareService;
  }

  public void validarPotenciaDaFonte(Computador computador) {
    if (computador.getFonte() == null) {
      throw new HardwareException("Nao existe fonte de alimentacao instalada.");
    }

    int consumo = hardwareService.calcularConsumoWattsEmTempoReal(computador);
    int margemSegura = (int) Math.ceil(consumo * 1.3);
    if (computador.getFonte().getPotenciaWatts() < margemSegura) {
      throw new HardwareException("Fonte insuficiente. Consumo estimado: " + consumo
          + "W, recomendado com margem: " + margemSegura + "W.");
    }
  }
}
