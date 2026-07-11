package service;

import model.seguranca.Antivirus;
import model.seguranca.Firewall;

public class SegurancaService {
  public void ativarProtecoes(Firewall firewall, Antivirus antivirus) {
    firewall.ativar();
    antivirus.ativarProtecao();
  }
}
