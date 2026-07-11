package model.software;

import model.enums.EstadoPrograma;
import model.interfaces.Atualizavel;

public class Programa implements Atualizavel {
  private final String nome;
  private String versao;
  private final int memoriaNecessariaMb;
  private EstadoPrograma estado;

  public Programa(String nome, String versao, int memoriaNecessariaMb) {
    this.nome = nome;
    this.versao = versao;
    this.memoriaNecessariaMb = memoriaNecessariaMb;
    this.estado = EstadoPrograma.INSTALADO;
  }

  public void executar() {
    estado = EstadoPrograma.EM_EXECUCAO;
  }

  public void parar() {
    estado = EstadoPrograma.PARADO;
  }

  public void remover() {
    estado = EstadoPrograma.REMOVIDO;
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

  public int getMemoriaNecessariaMb() {
    return memoriaNecessariaMb;
  }

  public EstadoPrograma getEstado() {
    return estado;
  }
}
