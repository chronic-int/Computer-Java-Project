package command;

import model.software.Programa;
import service.ProgramaService;

public class InstalarProgramaCommand implements Comando {
  private final ProgramaService programaService;
  private final Programa programa;

  public InstalarProgramaCommand(ProgramaService programaService, Programa programa) {
    this.programaService = programaService;
    this.programa = programa;
  }

  @Override
  public void executar() {
    programaService.instalar(programa);
  }
}
