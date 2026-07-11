package service;

import model.interfaces.Atualizavel;

public class AtualizacaoService {
  public void atualizar(Atualizavel atualizavel, String novaVersao) {
    atualizavel.atualizar(novaVersao);
  }
}
