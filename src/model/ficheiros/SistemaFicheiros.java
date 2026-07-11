package model.ficheiros;

import java.util.LinkedHashMap;
import java.util.Map;

public class SistemaFicheiros {
  private final Map<String, Pasta> pastas;

  public SistemaFicheiros() {
    this.pastas = new LinkedHashMap<>();
    criarPasta("/");
    criarPasta("/programas");
    criarPasta("/utilizadores");
  }

  public Pasta criarPasta(String caminho) {
    Pasta pasta = new Pasta(caminho);
    pastas.put(caminho, pasta);
    return pasta;
  }

  public Pasta obterPasta(String caminho) {
    return pastas.get(caminho);
  }

  public Map<String, Pasta> getPastas() {
    return pastas;
  }
}
