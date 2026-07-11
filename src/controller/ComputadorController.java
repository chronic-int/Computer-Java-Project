package controller;

import command.Comando;
import dto.ComputadorDTO;
import model.computador.Computador;

public class ComputadorController {
  private final Computador computador;

  public ComputadorController(Computador computador) {
    this.computador = computador;
  }

  public void executar(Comando comando) {
    comando.executar();
  }

  public ComputadorDTO resumo() {
    return new ComputadorDTO(computador.getMarca(), computador.getModelo(), computador.getEstado().name());
  }
}
