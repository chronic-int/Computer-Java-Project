package model.ficheiros;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pasta {
  private final String nome;
  private final List<Ficheiro> ficheiros;

  public Pasta(String nome) {
    this.nome = nome;
    this.ficheiros = new ArrayList<>();
  }

  public void adicionar(Ficheiro ficheiro) {
    ficheiros.add(ficheiro);
  }

  public String getNome() {
    return nome;
  }

  public List<Ficheiro> getFicheiros() {
    return Collections.unmodifiableList(ficheiros);
  }
}
