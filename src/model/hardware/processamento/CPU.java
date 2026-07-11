package model.hardware.processamento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.enums.TipoSocket;
import model.hardware.ComponenteHardware;

public class CPU extends ComponenteHardware {
  private final String marca;
  private final String modelo;
  private final TipoSocket socket;
  private final int nucleos;
  private final int threads;
  private final int tdpWatts;
  private long ciclosExecutados;
  private long instrucoesExecutadas;
  private double utilizacaoPercentagem;
  private final List<String> historicoInstrucoes;
  private boolean throttlingActivo;

  public CPU(String marca, String modelo, TipoSocket socket, int nucleos, int threads, int tdpWatts) {
    super();
    this.marca = marca;
    this.modelo = modelo;
    this.socket = socket;
    this.nucleos = nucleos;
    this.threads = threads;
    this.tdpWatts = tdpWatts;
    this.historicoInstrucoes = new ArrayList<>();
  }

  public String getNome() {
    return marca + " " + modelo;
  }

  public TipoSocket getSocket() {
    return socket;
  }

  public int getNucleos() {
    return nucleos;
  }

  public int getThreads() {
    return threads;
  }

  public int getTdpWatts() {
    return tdpWatts;
  }

  public String processarInstrucao(String instrucao) {
    long ciclosBase = Math.max(1, instrucao.length() / Math.max(1, threads)) + nucleos;
    long ciclos = throttlingActivo ? ciclosBase * 2 : ciclosBase;
    ciclosExecutados += ciclos;
    instrucoesExecutadas++;
    utilizacaoPercentagem = Math.min(100.0, (ciclos * 100.0) / Math.max(10, threads * nucleos));
    aquecer((tdpWatts / 55.0) * (utilizacaoPercentagem / 100.0));
    if (getTemperaturaAtual() > 90.0) {
      throttlingActivo = true;
    }
    if (getTemperaturaAtual() > 100.0) {
      queimar();
    }
    historicoInstrucoes.add(instrucao);
    if (historicoInstrucoes.size() > 20) {
      historicoInstrucoes.remove(0);
    }
    return "CPU executou " + instrucao + " em " + ciclos + " ciclos";
  }

  public List<String> executarPrograma(List<String> instrucoes) {
    List<String> resultados = new ArrayList<>();
    for (String instrucao : instrucoes) {
      resultados.add(processarInstrucao(instrucao));
    }
    return resultados;
  }

  public long getCiclosExecutados() {
    return ciclosExecutados;
  }

  public long getInstrucoesExecutadas() {
    return instrucoesExecutadas;
  }

  public double getUtilizacaoPercentagem() {
    return utilizacaoPercentagem;
  }

  public List<String> getHistoricoInstrucoes() {
    return Collections.unmodifiableList(historicoInstrucoes);
  }

  public boolean isThrottlingActivo() {
    return throttlingActivo;
  }

  public void setThrottlingActivo(boolean throttlingActivo) {
    this.throttlingActivo = throttlingActivo;
  }
}
