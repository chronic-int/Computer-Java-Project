package command;

import model.computador.Computador;
import service.BootService;

public class LigarComputadorCommand implements Comando {
  private final BootService bootService;
  private final Computador computador;

  public LigarComputadorCommand(BootService bootService, Computador computador) {
    this.bootService = bootService;
    this.computador = computador;
  }

  @Override
  public void executar() {
    System.out.println(bootService.ligar(computador));
  }
}
