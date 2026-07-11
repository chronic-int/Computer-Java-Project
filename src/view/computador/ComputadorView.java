package view.computador;

import model.computador.Computador;
import model.hardware.motherboard.Motherboard;
import model.hardware.slots.SlotM2;
import model.hardware.slots.SlotPCIe;
import model.hardware.slots.SlotRAM;
import service.HardwareService;

public class ComputadorView {
  public void mostrar(Computador computador, HardwareService hardwareService) {
    System.out.println();
    System.out.println("Computador: " + computador.getMarca() + " " + computador.getModelo());
    System.out.println("Estado: " + computador.getEstado());
    System.out.println("Gabinete: "
        + (computador.getGabinete() == null ? "ausente" : computador.getGabinete().getModelo()));
    System.out.println("Fonte: "
        + (computador.getFonte() == null ? "ausente" : computador.getFonte().getDescricao()));
    System.out.println("Sistema: " + (computador.getSistemaOperativo() == null
        ? "nao carregado"
        : computador.getSistemaOperativo().getNome() + " " + computador.getSistemaOperativo().getVersao()));

    if (computador.getMotherboard() == null) {
      System.out.println("Motherboard: ausente");
      return;
    }

    Motherboard motherboard = computador.getMotherboard();
    System.out.println("Motherboard: " + motherboard.getMarca() + " " + motherboard.getModelo());
    System.out.println("CPU: " + (motherboard.getSocketCPU().estaOcupado()
        ? motherboard.getSocketCPU().getCpu().getNome()
        : "ausente"));

    for (SlotRAM slot : motherboard.getSlotsRam()) {
      System.out.println("RAM slot " + slot.getNumero() + " canal " + slot.getCanal() + ": "
          + (slot.estaOcupado() ? slot.getModulo().getDescricao() : "vazio"));
    }
    for (SlotPCIe slot : motherboard.getSlotsPCIe()) {
      System.out.println("PCIe: " + (slot.estaOcupado() ? slot.getGpu().getDescricao() : "vazio"));
    }
    for (SlotM2 slot : motherboard.getSlotsM2()) {
      System.out.println("M.2: " + (slot.estaOcupado() ? slot.getSsd().getDescricao() : "vazio"));
    }

    System.out.println("Consumo estimado: " + hardwareService.calcularConsumoWatts(computador) + "W");
    System.out.println("Dual channel: "
        + (hardwareService.memoriaEmDualChannel(computador) ? "ativo pela posicao dos modulos" : "nao ativo"));
  }
}
