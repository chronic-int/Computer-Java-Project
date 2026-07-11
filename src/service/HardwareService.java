package service;

import exception.HardwareException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import model.computador.Computador;
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
    SlotRAM slotLivre = motherboard(computador).getSlotsRam().stream()
        .filter(slot -> !slot.estaOcupado())
        .findFirst()
        .orElseThrow(() -> new HardwareException("Nao ha slots RAM livres."));
    slotLivre.instalar(ram);
  }

  public void instalarGPU(Computador computador, GPU gpu) {
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
}
