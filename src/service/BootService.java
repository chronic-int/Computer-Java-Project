package service;

import exception.BootException;
import model.computador.Computador;
import model.enums.EstadoComputador;
import model.hardware.motherboard.Motherboard;

public class BootService {
  private final HardwareService hardwareService;
  private final EnergiaService energiaService;

  public BootService(HardwareService hardwareService, EnergiaService energiaService) {
    this.hardwareService = hardwareService;
    this.energiaService = energiaService;
  }

  public String ligar(Computador computador) {
    computador.setEstado(EstadoComputador.EM_POST);
    try {
      Motherboard motherboard = hardwareService.motherboard(computador);
      energiaService.validarPotenciaDaFonte(computador);

      if (!motherboard.getSocketCPU().estaOcupado()) {
        throw new BootException("POST falhou: CPU ausente.");
      }
      boolean existeRam = motherboard.getSlotsRam().stream().anyMatch(slot -> slot.estaOcupado());
      if (!existeRam) {
        throw new BootException("POST falhou: memoria RAM ausente.");
      }
      if (!hardwareService.encontrarDiscoComSistema(computador).isPresent()) {
        throw new BootException("POST concluiu, mas nao ha disco com sistema operativo.");
      }

      computador.setSistemaOperativo(hardwareService.encontrarDiscoComSistema(computador).get().getSistemaOperativo());
      computador.setEstado(EstadoComputador.LIGADO);
      return motherboard.getBios().inicializar()
          + System.lineSeparator()
          + "POST OK. Sistema operativo carregado a partir do SSD.";
    } catch (RuntimeException ex) {
      computador.setEstado(EstadoComputador.ERRO);
      throw ex;
    }
  }

  public void desligar(Computador computador) {
    computador.setEstado(EstadoComputador.DESLIGADO);
  }
}
