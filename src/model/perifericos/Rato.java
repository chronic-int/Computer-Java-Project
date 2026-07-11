package model.perifericos;

public class Rato {
  private final int dpi;

  public Rato(int dpi) {
    this.dpi = dpi;
  }

  public String getDescricao() {
    return "Rato " + dpi + " DPI";
  }
}
