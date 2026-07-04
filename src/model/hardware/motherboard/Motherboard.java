package model.Hardware.MotherBoard;

import model.enums.TipoMotherboard;
import model.enums.TipoSocket;

public class Motherboard {

  // Identidade

  private final String marca;
  private final String modelo;
  private final String numeroSerie;

  // Especificações

  private final TipoMotherboard tipo; // <- Variaveis de outra classe ainda será associada.
  private final TipoSocket socket;

  // capacidade

  private final int quantidadeSlotsRam;
  private final int quantidadeSlotsPCIe;
  private final int quantidadeSlotsM2;
  private final int quantidadeSlotsSATA;

  // Construtor
  public Motherboard(String marca, String modelo, String numeroSerie, TipoMotherboard tipo,
      TipoSocket socket, int quantidadeSlotsRam, int quantidadeSlotsPCIe, int quantidadeSlotsM2,
      int quantidadeSlotsSATA) {

    this.marca = marca;
    this.modelo = modelo;
    this.numeroSerie = numeroSerie;
    this.tipo = tipo;
    this.socket = socket;
    this.quantidadeSlotsRam = quantidadeSlotsRam;
    this.quantidadeSlotsPCIe = quantidadeSlotsPCIe;
    this.quantidadeSlotsM2 = quantidadeSlotsM2;
    this.quantidadeSlotsSATA = quantidadeSlotsSATA;
  }

  // Getters

  public String getMarca() {
    return marca;
  }

  public String getModelo() {
    return modelo;
  }

  public String getNumeroSerie() {
    return numeroSerie;
  }

  public String getTipo() {
    return tipo;
  }

  public String getSocket() {
    return socket;
  }

  public int getQuantidadeSlotsRam() {
    return quantidadeSlotsRam;
  }

  public int getQuantidadeSlotsPCIe() {
    return quantidadeSlotsPCIe;
  }

  public int getQuantidadeSlotsM2() {
    return quantidadeSlotsM2;
  }

  public int getQuantidadeSlotsSATA() {
    return quantidadeSlotsSATA;
  }
}
