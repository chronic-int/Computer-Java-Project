package controller;

import command.ExecutarProgramaCommand;
import command.InstalarProgramaCommand;
import model.computador.Computador;
import model.software.Programa;
import service.ProgramaService;

public class ProgramaController {
  private final Computador computador;
  private final ProgramaService programaService;

  public ProgramaController(Computador computador, ProgramaService programaService) {
    this.computador = computador;
    this.programaService = programaService;
  }

  public void instalar(Programa programa) {
    new InstalarProgramaCommand(programaService, programa).executar();
  }

  public void executar(String nomePrograma) {
    new ExecutarProgramaCommand(programaService, computador, nomePrograma).executar();
  }
}
