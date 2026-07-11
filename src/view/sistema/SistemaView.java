package view.sistema;

import dto.SistemaDTO;

public class SistemaView {
  public void mostrar(SistemaDTO sistemaDTO) {
    System.out.println("Sistema: " + sistemaDTO.getNome() + " " + sistemaDTO.getVersao()
        + " | processos: " + sistemaDTO.getProcessosAtivos());
  }
}
