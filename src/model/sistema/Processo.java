package model.sistema;

public class Processo {
  private static int proximoPid = 1000;

  private final int pid;
  private final String nome;
  private final int memoriaMb;
  private boolean ativo;

  public Processo(String nome, int memoriaMb) {
    this.pid = proximoPid++;
    this.nome = nome;
    this.memoriaMb = memoriaMb;
    this.ativo = true;
  }

  public int getPid() {
    return pid;
  }

  public String getNome() {
    return nome;
  }

  public int getMemoriaMb() {
    return memoriaMb;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void terminar() {
    this.ativo = false;
  }
}
