package model.hardware.motherboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.enums.TipoDDR;
import model.enums.TipoMotherboard;
import model.enums.TipoSocket;
import model.hardware.slots.SlotM2;
import model.hardware.slots.SlotPCIe;
import model.hardware.slots.SlotRAM;
import model.hardware.slots.SocketCPU;
import model.hardware.ComponenteHardware;
import model.sistema.BIOS;

public class Motherboard extends ComponenteHardware {
  private final String marca;
  private final String modelo;
  private final String numeroSerie;
  private final TipoMotherboard tipo;
  private final SocketCPU socketCPU;
  private final List<SlotRAM> slotsRam;
  private final List<SlotPCIe> slotsPCIe;
  private final List<SlotM2> slotsM2;
  private final BIOS bios;
  private boolean temPastaTermica;

  public Motherboard(String marca, String modelo, String numeroSerie, TipoMotherboard tipo,
      TipoSocket socket, TipoDDR tipoDDR, int quantidadeSlotsRam, int quantidadeSlotsPCIe,
      int quantidadeSlotsM2, BIOS bios) {
    super();
    this.marca = marca;
    this.modelo = modelo;
    this.numeroSerie = numeroSerie;
    this.tipo = tipo;
    this.socketCPU = new SocketCPU(socket);
    this.slotsRam = criarSlotsRam(quantidadeSlotsRam, tipoDDR);
    this.slotsPCIe = criarSlotsPCIe(quantidadeSlotsPCIe);
    this.slotsM2 = criarSlotsM2(quantidadeSlotsM2);
    this.bios = bios;
  }

  public String getMarca() {
    return marca;
  }

  public String getModelo() {
    return modelo;
  }

  public String getNumeroSerie() {
    return numeroSerie;
  }

  public TipoMotherboard getTipo() {
    return tipo;
  }

  public SocketCPU getSocketCPU() {
    return socketCPU;
  }

  public List<SlotRAM> getSlotsRam() {
    return Collections.unmodifiableList(slotsRam);
  }

  public List<SlotPCIe> getSlotsPCIe() {
    return Collections.unmodifiableList(slotsPCIe);
  }

  public List<SlotM2> getSlotsM2() {
    return Collections.unmodifiableList(slotsM2);
  }

  public BIOS getBios() {
    return bios;
  }

  public boolean temPastaTermica() {
    return temPastaTermica;
  }

  public void aplicarPastaTermica() {
    this.temPastaTermica = true;
  }

  private List<SlotRAM> criarSlotsRam(int quantidade, TipoDDR tipoDDR) {
    List<SlotRAM> slots = new ArrayList<>();
    for (int i = 1; i <= quantidade; i++) {
      String canal = i % 2 == 0 ? "B" : "A";
      slots.add(new SlotRAM(i, canal, tipoDDR));
    }
    return slots;
  }

  private List<SlotPCIe> criarSlotsPCIe(int quantidade) {
    List<SlotPCIe> slots = new ArrayList<>();
    for (int i = 1; i <= quantidade; i++) {
      slots.add(new SlotPCIe(i));
    }
    return slots;
  }

  private List<SlotM2> criarSlotsM2(int quantidade) {
    List<SlotM2> slots = new ArrayList<>();
    for (int i = 1; i <= quantidade; i++) {
      slots.add(new SlotM2(i));
    }
    return slots;
  }
}
