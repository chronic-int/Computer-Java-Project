package controller;

import dto.SistemaDTO;
import model.computador.Computador;
import service.SistemaService;

public class SistemaController {
  private final Computador computador;
  private final SistemaService sistemaService;

  public SistemaController(Computador computador, SistemaService sistemaService) {
    this.computador = computador;
    this.sistemaService = sistemaService;
  }

  public void instalarSistemaPadrao() {
    sistemaService.instalarSistemaOperativo(computador, sistemaService.criarSistemaPadrao());
  }

  public SistemaDTO resumo() {
    return sistemaService.criarResumo(computador);
  }
}
