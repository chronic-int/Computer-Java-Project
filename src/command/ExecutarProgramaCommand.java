package command;

import model.computador.Computador;
import service.ProgramaService;

public class ExecutarProgramaCommand implements Comando {
  private final ProgramaService programaService;
  private final Computador computador;
  private final String nomePrograma;

  public ExecutarProgramaCommand(ProgramaService programaService, Computador computador, String nomePrograma) {
    this.programaService = programaService;
    this.computador = computador;
    this.nomePrograma = nomePrograma;
  }

  @Override
  public void executar() {
    programaService.executar(computador, nomePrograma);
  }
}
