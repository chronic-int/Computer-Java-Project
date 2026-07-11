package service;

import java.util.Optional;
import exception.ProgramaException;
import model.computador.Computador;
import model.enums.EstadoComputador;
import model.software.Programa;
import repository.ProgramaRepository;

public class ProgramaService {
  private final ProgramaRepository programaRepository;

  public ProgramaService(ProgramaRepository programaRepository) {
    this.programaRepository = programaRepository;
  }

  public void instalar(Programa programa) {
    programaRepository.adicionar(programa);
  }

  public void executar(Computador computador, String nomePrograma) {
    if (computador.getEstado() != EstadoComputador.LIGADO) {
      throw new ProgramaException("O computador precisa estar ligado para executar programas.");
    }
    if (computador.getSistemaOperativo() == null) {
      throw new ProgramaException("Nao ha sistema operativo carregado.");
    }

    Optional<Programa> programaEncontrado = programaRepository.procurarPorNome(nomePrograma);
    if (!programaEncontrado.isPresent()) {
      throw new ProgramaException("Programa nao instalado: " + nomePrograma);
    }
    Programa programa = programaEncontrado.get();
    programa.executar();
    computador.getSistemaOperativo().iniciarProcesso(programa.getNome(), programa.getMemoriaNecessariaMb());
  }

  public ProgramaRepository getProgramaRepository() {
    return programaRepository;
  }
}
