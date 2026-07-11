package controller;

import model.computador.Computador;
import model.hardware.armazenamento.SSD;
import model.hardware.energia.Fonte;
import model.hardware.gabinete.Gabinete;
import model.hardware.grafico.GPU;
import model.hardware.memoria.RAM;
import model.hardware.motherboard.Motherboard;
import model.hardware.processamento.CPU;
import service.HardwareService;

public class HardwareController {
  private final Computador computador;
  private final HardwareService hardwareService;

  public HardwareController(Computador computador, HardwareService hardwareService) {
    this.computador = computador;
    this.hardwareService = hardwareService;
  }

  public void instalarGabinete(Gabinete gabinete) {
    hardwareService.instalarGabinete(computador, gabinete);
  }

  public void instalarFonte(Fonte fonte) {
    hardwareService.instalarFonte(computador, fonte);
  }

  public void instalarMotherboard(Motherboard motherboard) {
    hardwareService.instalarMotherboard(computador, motherboard);
  }

  public void instalarCPU(CPU cpu) {
    hardwareService.instalarCPU(computador, cpu);
  }

  public void instalarRAM(RAM ram) {
    hardwareService.instalarRAM(computador, ram);
  }

  public void instalarGPU(GPU gpu) {
    hardwareService.instalarGPU(computador, gpu);
  }

  public void instalarSSD(SSD ssd) {
    hardwareService.instalarSSD(computador, ssd);
  }
}
