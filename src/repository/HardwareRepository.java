package repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HardwareRepository {
  private final List<Object> pecas = new ArrayList<>();

  public void adicionar(Object peca) {
    pecas.add(peca);
  }

  public List<Object> listar() {
    return Collections.unmodifiableList(pecas);
  }
}
