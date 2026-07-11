package model.perifericos;

public class Monitor {
  private final String resolucao;
  private final int polegadas;

  public Monitor(String resolucao, int polegadas) {
    this.resolucao = resolucao;
    this.polegadas = polegadas;
  }

  public String getDescricao() {
    return "Monitor " + polegadas + "\" " + resolucao;
  }
}
