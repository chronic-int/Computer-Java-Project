package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import model.software.Programa;

public class ProgramaRepository {
  private final List<Programa> programas = new ArrayList<>();

  public void adicionar(Programa programa) {
    programas.add(programa);
  }

  public Optional<Programa> procurarPorNome(String nome) {
    for (Programa programa : programas) {
      if (programa.getNome().equalsIgnoreCase(nome)) {
        return Optional.of(programa);
      }
    }
    return Optional.empty();
  }

  public List<Programa> listar() {
    return Collections.unmodifiableList(programas);
  }
}
