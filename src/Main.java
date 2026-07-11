import app.JogoMontagemComputador;

public class Main {
  public static void main(String[] args) {
    if (args.length > 0 && "console".equalsIgnoreCase(args[0])) {
      new JogoMontagemComputador().iniciar();
      return;
    }
    app.ComputadorRealistaApp.iniciar();
  }
}
