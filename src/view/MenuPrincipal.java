package view;

public class MenuPrincipal {
  private final ConsoleView consoleView;
  private final Menus menus;

  public MenuPrincipal(ConsoleView consoleView, Menus menus) {
    this.consoleView = consoleView;
    this.menus = menus;
  }

  public void mostrar(String estado) {
    consoleView.linhaVazia();
    System.out.print(menus.menuPrincipal(estado));
  }
}
