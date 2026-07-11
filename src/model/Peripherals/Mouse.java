package model.Peripherals;

public class Mouse {
  private final int dpi;

  public Mouse() {
    this(1600);
  }

  public Mouse(int dpi) {
    this.dpi = dpi;
  }

  public int getDpi() {
    return dpi;
  }
}
