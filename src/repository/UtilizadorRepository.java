package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.sistema.Utilizador;

public class UtilizadorRepository {
  private final List<Utilizador> utilizadores = new ArrayList<>();

  public void adicionar(Utilizador utilizador) {
    utilizadores.add(utilizador);
  }

  public List<Utilizador> listar() {
    return Collections.unmodifiableList(utilizadores);
  }
}
