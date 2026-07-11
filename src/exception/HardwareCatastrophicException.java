package exception;

/**
 * Representa uma falha fisica irreversivel no computador simulado.
 *
 * <p>Ao contrario de uma {@link HardwareException}, esta excepcao indica que
 * houve dano permanente em componentes, como curto-circuito, sobrecarga da
 * fonte ou silicio queimado por temperatura excessiva.</p>
 */
public class HardwareCatastrophicException extends HardwareException {
  private final String codigoParagem;

  public HardwareCatastrophicException(String mensagem, String codigoParagem) {
    super(mensagem);
    this.codigoParagem = codigoParagem;
  }

  public String getCodigoParagem() {
    return codigoParagem;
  }
}
