package model.perifericos;

public class Webcam {
  private final String resolucao;

  public Webcam(String resolucao) {
    this.resolucao = resolucao;
  }

  public String getDescricao() {
    return "Webcam " + resolucao;
  }
}
