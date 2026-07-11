package model.Peripherals;

public class Keyboard {
  private final String layout;

  public Keyboard() {
    this("US");
  }

  public Keyboard(String layout) {
    this.layout = layout;
  }

  public String type(String text) {
    return text;
  }

  public String getLayout() {
    return layout;
  }
}
