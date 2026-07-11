package repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import model.computador.Computador;

public class ComputadorRepository {
  private final Map<String, Computador> computadores = new LinkedHashMap<>();

  public void guardar(String id, Computador computador) {
    computadores.put(id, computador);
  }

  public Computador obter(String id) {
    return computadores.get(id);
  }

  public Collection<Computador> listar() {
    return computadores.values();
  }
}
