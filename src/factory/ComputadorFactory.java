package factory;

import model.computador.Computador;

public class ComputadorFactory {
  public Computador criarComputadorAprendizagem() {
    return new Computador("Oficina Java", "PC de Aprendizagem");
  }
}
