package service;

import dto.SistemaDTO;
import exception.SistemaException;
import model.computador.Computador;
import model.enums.SistemaOperativoTipo;
import model.sistema.SistemaOperativo;

public class SistemaService {
  public void instalarSistemaOperativo(Computador computador, SistemaOperativo sistemaOperativo) {
    if (computador.getEstado() == null) {
      throw new SistemaException("Computador invalido.");
    }
    computador.setSistemaOperativo(sistemaOperativo);
  }

  public SistemaOperativo criarSistemaPadrao() {
    return new SistemaOperativo("JavaOS", "1.0", SistemaOperativoTipo.LINUX);
  }

  public SistemaDTO criarResumo(Computador computador) {
    SistemaOperativo sistema = computador.getSistemaOperativo();
    if (sistema == null) {
      return new SistemaDTO("Sem sistema", "0", 0);
    }
    return new SistemaDTO(sistema.getNome(), sistema.getVersao(), sistema.getProcessos().size());
  }
}
