package service;

import model.computador.Computador;

public class DiagnosticoService {
  private final HardwareService hardwareService;

  public DiagnosticoService(HardwareService hardwareService) {
    this.hardwareService = hardwareService;
  }

  public String gerarRelatorio(Computador computador) {
    StringBuilder relatorio = new StringBuilder();
    relatorio.append("Estado: ").append(computador.getEstado()).append(System.lineSeparator());
    relatorio.append("Motherboard: ").append(computador.getMotherboard() == null ? "ausente" : "instalada")
        .append(System.lineSeparator());
    if (computador.getMotherboard() != null) {
      relatorio.append("Consumo estimado: ").append(hardwareService.calcularConsumoWatts(computador))
          .append("W").append(System.lineSeparator());
      relatorio.append("Dual channel: ").append(hardwareService.memoriaEmDualChannel(computador));
    }
    return relatorio.toString();
  }
}
