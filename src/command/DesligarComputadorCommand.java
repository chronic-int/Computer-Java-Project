package command;

import model.computador.Computador;
import service.BootService;

public class DesligarComputadorCommand implements Comando {
  private final BootService bootService;
  private final Computador computador;

  public DesligarComputadorCommand(BootService bootService, Computador computador) {
    this.bootService = bootService;
    this.computador = computador;
  }

  @Override
  public void executar() {
    bootService.desligar(computador);
  }
}
