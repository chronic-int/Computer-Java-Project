package model.perifericos;

public class Teclado {
  private final String layout;

  public Teclado(String layout) {
    this.layout = layout;
  }

  public String getDescricao() {
    return "Teclado " + layout;
  }
}
