package model.drivers;

import model.interfaces.Atualizavel;

public class Driver implements Atualizavel {
  private final String nome;
  private String versao;
  private final String dispositivo;

  public Driver(String nome, String versao, String dispositivo) {
    this.nome = nome;
    this.versao = versao;
    this.dispositivo = dispositivo;
  }

  @Override
  public void atualizar(String novaVersao) {
    this.versao = novaVersao;
  }

  public String getNome() {
    return nome;
  }

  public String getVersao() {
    return versao;
  }

  public String getDispositivo() {
    return dispositivo;
  }
}
