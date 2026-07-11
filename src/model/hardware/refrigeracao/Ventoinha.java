package model.hardware.refrigeracao;

public class Ventoinha {
  private final int diametroMm;
  private int rpm;

  public Ventoinha(int diametroMm) {
    this.diametroMm = diametroMm;
  }

  public void definirRpm(int rpm) {
    this.rpm = rpm;
  }

  public int getDiametroMm() {
    return diametroMm;
  }

  public int getRpm() {
    return rpm;
  }
}
