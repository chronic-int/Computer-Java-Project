package service;

import exception.HardwareCatastrophicException;
import exception.HardwareException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import model.computador.Computador;
import model.enums.EstadoComputador;
import model.hardware.armazenamento.SSD;
import model.hardware.energia.Fonte;
import model.hardware.gabinete.Gabinete;
import model.hardware.grafico.GPU;
import model.hardware.memoria.RAM;
import model.hardware.motherboard.Motherboard;
import model.hardware.processamento.CPU;
import model.hardware.slots.SlotM2;
import model.hardware.slots.SlotPCIe;
import model.hardware.slots.SlotRAM;

public class HardwareService {
  private final Random random = new Random();
  private ScheduledExecutorService simulador;

  public void instalarGabinete(Computador computador, Gabinete gabinete) {
    computador.setGabinete(gabinete);
  }

  public void instalarFonte(Computador computador, Fonte fonte) {
    computador.setFonte(fonte);
  }

  public void instalarMotherboard(Computador computador, Motherboard motherboard) {
    if (computador.getGabinete() == null) {
      throw new HardwareException("Instale primeiro o gabinete fisico.");
    }
    if (!computador.getGabinete().suporta(motherboard.getTipo())) {
      throw new HardwareException("O gabinete nao suporta motherboard " + motherboard.getTipo() + ".");
    }
    computador.setMotherboard(motherboard);
  }

  public void instalarCPU(Computador computador, CPU cpu) {
    motherboard(computador).getSocketCPU().instalar(cpu);
  }

  public void instalarRAM(Computador computador, RAM ram) {
    aplicarRiscoEstatico(computador, ram, "RAM");
    SlotRAM slotLivre = motherboard(computador).getSlotsRam().stream()
        .filter(slot -> !slot.estaOcupado())
        .findFirst()
        .orElseThrow(() -> new HardwareException("Nao ha slots RAM livres."));
    slotLivre.instalar(ram);
  }

  public void instalarGPU(Computador computador, GPU gpu) {
    aplicarRiscoEstatico(computador, gpu, "GPU");
    SlotPCIe slotLivre = motherboard(computador).getSlotsPCIe().stream()
        .filter(slot -> !slot.estaOcupado())
        .findFirst()
        .orElseThrow(() -> new HardwareException("Nao ha slots PCIe livres."));
    slotLivre.instalar(gpu);
  }

  public void instalarSSD(Computador computador, SSD ssd) {
    SlotM2 slotLivre = motherboard(computador).getSlotsM2().stream()
        .filter(slot -> !slot.estaOcupado())
        .findFirst()
        .orElseThrow(() -> new HardwareException("Nao ha slots M.2 livres."));
    slotLivre.instalar(ssd);
  }

  public int calcularConsumoWatts(Computador computador) {
    Motherboard motherboard = motherboard(computador);
    int consumo = 35;

    if (motherboard.getSocketCPU().estaOcupado()) {
      consumo += motherboard.getSocketCPU().getCpu().getTdpWatts();
    }
    for (SlotRAM slot : motherboard.getSlotsRam()) {
      if (slot.estaOcupado()) {
        consumo += slot.getModulo().getConsumoWatts();
      }
    }
    for (SlotPCIe slot : motherboard.getSlotsPCIe()) {
      if (slot.estaOcupado()) {
        consumo += slot.getGpu().getConsumoWatts();
      }
    }
    for (SlotM2 slot : motherboard.getSlotsM2()) {
      if (slot.estaOcupado()) {
        consumo += slot.getSsd().getConsumoWatts();
      }
    }

    return consumo;
  }

  public int calcularConsumoWattsEmTempoReal(Computador computador) {
    int consumo = calcularConsumoWatts(computador);
    if (computador.getSistemaOperativo() == null) {
      return consumo;
    }

    boolean programaPesado = computador.getSistemaOperativo().getProcessos().stream()
        .anyMatch(processo -> processo.isAtivo() && processo.getMemoriaMb() >= 1024);
    if (programaPesado) {
      consumo += 120;
    }
    return consumo;
  }

  public void iniciarSimulacao(Computador computador) {
    pararSimulacao();
    simulador = Executors.newSingleThreadScheduledExecutor();
    simulador.scheduleAtFixedRate(() -> simularTick(computador), 1, 1, TimeUnit.SECONDS);
  }

  public void pararSimulacao() {
    if (simulador != null) {
      simulador.shutdownNow();
      simulador = null;
    }
  }

  public void simularTick(Computador computador) {
    if (computador.getEstado() != EstadoComputador.LIGADO) {
      return;
    }
    try {
      validarComponentesCriticos(computador);
      validarSobrecarga(computador);
      simularTemperaturaCpu(computador);
      simularInstabilidadeMemoriaEGrafica(computador);
    } catch (HardwareCatastrophicException ex) {
      computador.registarErroFatal(ex.getMessage(), ex.getCodigoParagem());
      pararSimulacao();
    }
  }

  public void instalarEspacadores(Computador computador) {
    if (computador.getGabinete() == null) {
      throw new HardwareException("Instale primeiro o gabinete.");
    }
    computador.getGabinete().instalarEspacadores();
  }

  public void aplicarPastaTermica(Computador computador) {
    motherboard(computador).aplicarPastaTermica();
  }

  public void ligarCaboEpsCpu(Computador computador) {
    fonte(computador).ligarCaboEpsCpu();
  }

  public void ligarCaboPcieGpu(Computador computador) {
    fonte(computador).ligarCaboPcieGpu();
  }

  public void descarregarEstaticidade(Computador computador) {
    computador.setPulseiraAntiestaticaActiva(true);
  }

  public boolean memoriaEmDualChannel(Computador computador) {
    Set<String> canaisOcupados = new HashSet<>();
    for (SlotRAM slot : motherboard(computador).getSlotsRam()) {
      if (slot.estaOcupado()) {
        canaisOcupados.add(slot.getCanal());
      }
    }
    return canaisOcupados.size() >= 2;
  }

  public Optional<SSD> encontrarDiscoComSistema(Computador computador) {
    return motherboard(computador).getSlotsM2().stream()
        .filter(SlotM2::estaOcupado)
        .map(SlotM2::getSsd)
        .filter(SSD::contemSistemaOperativo)
        .findFirst();
  }

  public Motherboard motherboard(Computador computador) {
    if (computador.getMotherboard() == null) {
      throw new HardwareException("A motherboard ainda nao foi instalada.");
    }
    return computador.getMotherboard();
  }

  private Fonte fonte(Computador computador) {
    if (computador.getFonte() == null) {
      throw new HardwareException("Nao existe fonte de alimentacao instalada.");
    }
    return computador.getFonte();
  }

  private void aplicarRiscoEstatico(Computador computador, model.hardware.ComponenteHardware componente,
      String nome) {
    if (!computador.isPulseiraAntiestaticaActiva()) {
      double dano = 5.0 + random.nextDouble() * 25.0;
      componente.degradar(dano);
      computador.adicionarPeriferico("Aviso oculto: " + nome + " sofreu descarga estatica de "
          + String.format("%.1f", dano) + "%.");
    }
  }

  private void validarComponentesCriticos(Computador computador) {
    Motherboard motherboard = motherboard(computador);
    Fonte fonte = fonte(computador);
    if (motherboard.isQueimado() || fonte.isQueimado()) {
      throw new HardwareCatastrophicException("POST impossivel: placa ou fonte queimada.",
          "WHEA_UNCORRECTABLE_ERROR");
    }
    if (motherboard.getSocketCPU().estaOcupado() && motherboard.getSocketCPU().getCpu().isQueimado()) {
      throw new HardwareCatastrophicException("CPU queimada. O sistema perdeu a unidade de processamento.",
          "CLOCK_WATCHDOG_TIMEOUT");
    }
  }

  private void validarSobrecarga(Computador computador) {
    Fonte fonte = fonte(computador);
    int consumo = calcularConsumoWattsEmTempoReal(computador);
    if (consumo > fonte.getPotenciaWatts()) {
      double excesso = consumo - fonte.getPotenciaWatts();
      double probabilidade = Math.min(0.95, excesso / Math.max(1.0, fonte.getPotenciaWatts()));
      if (random.nextDouble() < probabilidade) {
        fonte.queimar();
        motherboard(computador).queimar();
        throw new HardwareCatastrophicException("Sobrecarga electrica: fonte e motherboard queimadas.",
            "WHEA_UNCORRECTABLE_ERROR");
      }
      fonte.aquecer(8.0 + excesso / 20.0);
      fonte.degradar(2.0 + excesso / 100.0);
    }
  }

  private void simularTemperaturaCpu(Computador computador) {
    Motherboard motherboard = motherboard(computador);
    if (!motherboard.getSocketCPU().estaOcupado()) {
      return;
    }
    CPU cpu = motherboard.getSocketCPU().getCpu();
    double dissipacao = motherboard.temPastaTermica() ? 8.0 : 0.8;
    boolean programaPesado = computador.getSistemaOperativo() != null
        && computador.getSistemaOperativo().getProcessos().stream()
            .anyMatch(processo -> processo.isAtivo() && processo.getMemoriaMb() >= 1024);
    cpu.aquecer(programaPesado ? 5.5 : 1.2);
    cpu.arrefecer(dissipacao);
    cpu.setThrottlingActivo(cpu.getTemperaturaAtual() > 90.0);
    if (cpu.getTemperaturaAtual() > 100.0 || cpu.isQueimado()) {
      cpu.queimar();
      throw new HardwareCatastrophicException("CPU queimou por temperatura excessiva.",
          "CLOCK_WATCHDOG_TIMEOUT");
    }
  }

  private void simularInstabilidadeMemoriaEGrafica(Computador computador) {
    Motherboard motherboard = motherboard(computador);
    for (SlotRAM slot : motherboard.getSlotsRam()) {
      if (slot.estaOcupado() && slot.getModulo().getIntegridade() < 75.0 && random.nextDouble() < 0.08) {
        throw new HardwareCatastrophicException("Falha de memoria causada por degradacao electrostatica.",
            "PAGE_FAULT_IN_NONPAGED_AREA");
      }
    }
    for (SlotPCIe slot : motherboard.getSlotsPCIe()) {
      if (slot.estaOcupado() && slot.getGpu().getIntegridade() < 75.0 && random.nextDouble() < 0.08) {
        throw new HardwareCatastrophicException("Instabilidade grafica: GPU degradada.",
            "VIDEO_TDR_FAILURE");
      }
    }
  }
}
