package util;

public final class Formatador {
  private Formatador() {
  }

  public static String watts(int valor) {
    return valor + "W";
  }

  public static String gigabytes(int valor) {
    return valor + "GB";
  }
}
