package model.computador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.enums.EstadoComputador;
import model.ficheiros.SistemaFicheiros;
import model.hardware.energia.Fonte;
import model.hardware.gabinete.Gabinete;
import model.hardware.motherboard.Motherboard;
import model.sistema.SistemaOperativo;

public class Computador {
  private final String marca;
  private final String modelo;
  private Motherboard motherboard;
  private Gabinete gabinete;
  private Fonte fonte;
  private SistemaOperativo sistemaOperativo;
  private final SistemaFicheiros sistemaFicheiros;
  private EstadoComputador estado;
  private final List<String> perifericos;
  private boolean pulseiraAntiestaticaActiva;
  private String erroFatal;
  private String codigoParagem;

  public Computador(String marca, String modelo) {
    this.marca = marca;
    this.modelo = modelo;
    this.sistemaFicheiros = new SistemaFicheiros();
    this.estado = EstadoComputador.DESLIGADO;
    this.perifericos = new ArrayList<>();
  }

  public String getMarca() {
    return marca;
  }

  public String getModelo() {
    return modelo;
  }

  public Motherboard getMotherboard() {
    return motherboard;
  }

  public void setMotherboard(Motherboard motherboard) {
    this.motherboard = motherboard;
  }

  public Gabinete getGabinete() {
    return gabinete;
  }

  public void setGabinete(Gabinete gabinete) {
    this.gabinete = gabinete;
  }

  public Fonte getFonte() {
    return fonte;
  }

  public void setFonte(Fonte fonte) {
    this.fonte = fonte;
  }

  public SistemaOperativo getSistemaOperativo() {
    return sistemaOperativo;
  }

  public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
    this.sistemaOperativo = sistemaOperativo;
  }

  public SistemaFicheiros getSistemaFicheiros() {
    return sistemaFicheiros;
  }

  public EstadoComputador getEstado() {
    return estado;
  }

  public void setEstado(EstadoComputador estado) {
    this.estado = estado;
  }

  public List<String> getPerifericos() {
    return Collections.unmodifiableList(perifericos);
  }

  public void adicionarPeriferico(String periferico) {
    perifericos.add(periferico);
  }

  public boolean isPulseiraAntiestaticaActiva() {
    return pulseiraAntiestaticaActiva;
  }

  public void setPulseiraAntiestaticaActiva(boolean pulseiraAntiestaticaActiva) {
    this.pulseiraAntiestaticaActiva = pulseiraAntiestaticaActiva;
  }

  public String getErroFatal() {
    return erroFatal;
  }

  public String getCodigoParagem() {
    return codigoParagem;
  }

  public void registarErroFatal(String erroFatal, String codigoParagem) {
    this.erroFatal = erroFatal;
    this.codigoParagem = codigoParagem;
    this.estado = EstadoComputador.ERRO;
  }

  public void limparErroFatal() {
    this.erroFatal = null;
    this.codigoParagem = null;
  }
}
