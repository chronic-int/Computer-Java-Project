package factory;

import model.software.EditorCodigo;
import model.software.Jogo;
import model.software.Navegador;
import model.software.Programa;

public class ProgramaFactory {
  public Programa criarNavegador() {
    return new Navegador();
  }

  public Programa criarEditorCodigo() {
    return new EditorCodigo();
  }

  public Programa criarJogo() {
    return new Jogo();
  }

  public Programa criarTerminal() {
    return new Programa("Terminal", "1.0", 128);
  }

  public Programa criarGestorFicheiros() {
    return new Programa("Gestor de Ficheiros", "1.0", 180);
  }

  public Programa criarLojaSoftware() {
    return new Programa("Loja de Software", "1.0", 256);
  }
}
