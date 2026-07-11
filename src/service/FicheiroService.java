package service;

import exception.FicheiroException;
import model.computador.Computador;
import model.ficheiros.Ficheiro;
import model.ficheiros.Pasta;

public class FicheiroService {
  public void criarFicheiro(Computador computador, String pasta, String nome, String conteudo) {
    Pasta destino = computador.getSistemaFicheiros().obterPasta(pasta);
    if (destino == null) {
      throw new FicheiroException("Pasta inexistente: " + pasta);
    }
    destino.adicionar(new Ficheiro(nome, conteudo));
  }
}
