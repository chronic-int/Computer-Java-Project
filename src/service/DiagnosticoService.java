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
      relatorio.append("Consumo em tempo real: ").append(hardwareService.calcularConsumoWattsEmTempoReal(computador))
          .append("W").append(System.lineSeparator());
      relatorio.append("Dual channel: ").append(hardwareService.memoriaEmDualChannel(computador))
          .append(System.lineSeparator());
      relatorio.append("Motherboard queimada: ").append(computador.getMotherboard().isQueimado())
          .append(System.lineSeparator());
      if (computador.getMotherboard().getSocketCPU().estaOcupado()) {
        relatorio.append("CPU temperatura: ")
            .append(String.format("%.1f", computador.getMotherboard().getSocketCPU().getCpu().getTemperaturaAtual()))
            .append("C").append(System.lineSeparator());
        relatorio.append("CPU integridade: ")
            .append(String.format("%.1f", computador.getMotherboard().getSocketCPU().getCpu().getIntegridade()))
            .append("%").append(System.lineSeparator());
      }
      if (computador.getFonte() != null) {
        relatorio.append("Fonte queimada: ").append(computador.getFonte().isQueimado())
            .append(System.lineSeparator());
      }
    }
    if (computador.getErroFatal() != null) {
      relatorio.append("STOP CODE: ").append(computador.getCodigoParagem()).append(System.lineSeparator());
      relatorio.append("Erro fatal: ").append(computador.getErroFatal()).append(System.lineSeparator());
    }
    return relatorio.toString();
  }
}
