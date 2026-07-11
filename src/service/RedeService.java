package service;

import exception.RedeException;
import model.hardware.portas.PortaEthernet;
import model.hardware.rede.PlacaRede;

public class RedeService {
  public String conectar(PlacaRede placaRede, PortaEthernet portaEthernet) {
    if (placaRede == null) {
      throw new RedeException("Nao existe placa de rede.");
    }
    portaEthernet.conectarCabo();
    return "Rede ligada a " + placaRede.getVelocidadeMbps() + " Mbps.";
  }
}
