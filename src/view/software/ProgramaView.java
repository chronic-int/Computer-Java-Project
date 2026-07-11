package view.software;

import model.software.Programa;

public class ProgramaView {
  public void instalado(Programa programa) {
    System.out.println("Programa instalado: " + programa.getNome());
  }

  public void executado(String nome) {
    System.out.println("Programa em execucao: " + nome);
  }
}
