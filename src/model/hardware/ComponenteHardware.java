package model.hardware;

/**
 * Contrato fisico comum para componentes sujeitos a desgaste, temperatura e
 * dano permanente no simulador.
 */
public abstract class ComponenteHardware {
  private double integridade;
  private double temperaturaAtual;
  private boolean queimado;

  protected ComponenteHardware() {
    this.integridade = 100.0;
    this.temperaturaAtual = 25.0;
    this.queimado = false;
  }

  public double getIntegridade() {
    return integridade;
  }

  public double getTemperaturaAtual() {
    return temperaturaAtual;
  }

  public boolean isQueimado() {
    return queimado;
  }

  public void aquecer(double graus) {
    if (queimado) {
      return;
    }
    temperaturaAtual += Math.max(0.0, graus);
  }

  public void arrefecer(double graus) {
    if (queimado) {
      return;
    }
    temperaturaAtual = Math.max(25.0, temperaturaAtual - Math.max(0.0, graus));
  }

  public void degradar(double pontos) {
    if (queimado) {
      return;
    }
    integridade = Math.max(0.0, integridade - Math.max(0.0, pontos));
    if (integridade <= 0.0) {
      queimar();
    }
  }

  public void queimar() {
    queimado = true;
    integridade = 0.0;
    temperaturaAtual = Math.max(temperaturaAtual, 120.0);
  }
}
