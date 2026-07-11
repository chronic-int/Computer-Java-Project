package model.sistema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.enums.SistemaOperativoTipo;
import model.interfaces.Atualizavel;

public class SistemaOperativo implements Atualizavel {
  private final String nome;
  private String versao;
  private final SistemaOperativoTipo tipo;
  private final List<Processo> processos;

  public SistemaOperativo(String nome, String versao, SistemaOperativoTipo tipo) {
    this.nome = nome;
    this.versao = versao;
    this.tipo = tipo;
    this.processos = new ArrayList<>();
  }

  public Processo iniciarProcesso(String nome, int memoriaMb) {
    Processo processo = new Processo(nome, memoriaMb);
    processos.add(processo);
    return processo;
  }

  public void terminarProcesso(int pid) {
    for (Processo processo : processos) {
      if (processo.getPid() == pid) {
        processo.terminar();
        return;
      }
    }
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

  public SistemaOperativoTipo getTipo() {
    return tipo;
  }

  public List<Processo> getProcessos() {
    return Collections.unmodifiableList(processos);
  }
}
