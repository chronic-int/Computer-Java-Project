package factory;

import model.enums.TipoDDR;
import model.enums.TipoMotherboard;
import model.enums.TipoSocket;
import model.hardware.armazenamento.SSD;
import model.hardware.energia.Fonte;
import model.hardware.gabinete.Gabinete;
import model.hardware.grafico.GPU;
import model.hardware.memoria.RAM;
import model.hardware.motherboard.Motherboard;
import model.hardware.processamento.CPU;
import model.sistema.BIOS;
import model.enums.SistemaOperativoTipo;

public class HardwareFactory {
  public Gabinete criarGabinetePadrao() {
    return new Gabinete("Mid Tower Airflow", TipoMotherboard.ATX);
  }

  public Fonte criarFontePadrao() {
    return new Fonte("Corsair", 650, "80 Plus Bronze");
  }

  public Motherboard criarMotherboardPadrao() {
    return new Motherboard("ASUS", "Prime B650", "MB-2026-0001", TipoMotherboard.ATX,
        TipoSocket.AM5, TipoDDR.DDR5, 4, 2, 2, new BIOS("AMI", "1.0.0"));
  }

  public CPU criarCpuPadrao() {
    return new CPU("AMD", "Ryzen 5 7600", TipoSocket.AM5, 6, 12, 65);
  }

  public RAM criarRamPadrao() {
    return new RAM("Kingston Fury", 16, TipoDDR.DDR5, 5600);
  }

  public GPU criarGpuPadrao() {
    return new GPU("NVIDIA", "RTX 4060", 8, 115);
  }

  public SSD criarSsdComSistema() {
    return new SSD("Samsung", "980", 1000, true);
  }

  public SSD criarSsdComSistema(SistemaOperativoTipo tipo) {
    return new SSD("Samsung", "980", 1000, tipo);
  }
}
