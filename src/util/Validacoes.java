package util;

public final class Validacoes {
  private Validacoes() {
  }

  public static void naoNulo(Object valor, String mensagem) {
    if (valor == null) {
      throw new IllegalArgumentException(mensagem);
    }
  }
}
