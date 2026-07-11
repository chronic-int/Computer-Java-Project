package app;

import command.DesligarComputadorCommand;
import command.LigarComputadorCommand;
import exception.BootException;
import exception.HardwareCatastrophicException;
import exception.HardwareException;
import java.util.Scanner;
import model.computador.Computador;
import model.drivers.DriverAudio;
import model.drivers.DriverGrafico;
import model.drivers.DriverRede;
import model.hardware.portas.PortaEthernet;
import model.hardware.rede.PlacaRede;
import model.seguranca.Antivirus;
import model.seguranca.Firewall;

public class JogoMontagemComputador {
  private final Scanner scanner;
  private final AppConfig config;
  private final Computador computador;

  public JogoMontagemComputador() {
    this.scanner = new Scanner(System.in);
    this.config = new AppConfig();
    this.computador = config.getComputador();
  }

  public void iniciar() {
    int opcao;
    do {
      mostrarMenu();
      opcao = lerOpcao();
      executar(opcao);
    } while (opcao != 0);
  }

  private void mostrarMenu() {
    config.getMenuPrincipal().mostrar(computador.getEstado().name());
  }

  private int lerOpcao() {
    while (!scanner.hasNextInt()) {
      scanner.nextLine();
      System.out.print("Digite um numero: ");
    }
    int opcao = scanner.nextInt();
    scanner.nextLine();
    return opcao;
  }

  private void executar(int opcao) {
    try {
      switch (opcao) {
        case 1:
          instalarGabinete();
          break;
        case 2:
          instalarFonte();
          break;
        case 3:
          instalarMotherboard();
          break;
        case 4:
          instalarCPU();
          break;
        case 5:
          instalarRAM();
          break;
        case 6:
          instalarGPU();
          break;
        case 7:
          instalarSSD();
          break;
        case 8:
          mostrarComputador();
          break;
        case 9:
          ligar();
          break;
        case 10:
          desligar();
          break;
        case 11:
          instalarProgramasBase();
          break;
        case 12:
          executarNavegador();
          break;
        case 13:
          instalarDrivers();
          break;
        case 14:
          criarFicheiroTeste();
          break;
        case 15:
          testarRede();
          break;
        case 16:
          ativarSeguranca();
          break;
        case 17:
          diagnostico();
          break;
        case 18:
          usarPulseiraAntiestatica();
          break;
        case 19:
          instalarEspacadores();
          break;
        case 20:
          aplicarPastaTermica();
          break;
        case 21:
          ligarCaboEps();
          break;
        case 22:
          ligarCaboPcie();
          break;
        case 23:
          executarJogo3D();
          break;
        case 0:
          config.getHardwareService().pararSimulacao();
          System.out.println("A sair da oficina.");
          break;
        default:
          System.out.println("Opcao invalida.");
          break;
      }
    } catch (HardwareCatastrophicException ex) {
      renderizarBsod(ex.getCodigoParagem(), ex.getMessage());
    } catch (HardwareException | BootException ex) {
      System.out.println("Falha: " + ex.getMessage());
    }
  }

  private void instalarGabinete() {
    config.getHardwareController().instalarGabinete(config.getHardwareFactory().criarGabinetePadrao());
    config.getHardwareView().sucesso("Gabinete instalado.");
  }

  private void instalarFonte() {
    config.getHardwareController().instalarFonte(config.getHardwareFactory().criarFontePadrao());
    config.getHardwareView().sucesso("Fonte instalada.");
  }

  private void instalarMotherboard() {
    config.getHardwareController().instalarMotherboard(config.getHardwareFactory().criarMotherboardPadrao());
    config.getHardwareView().sucesso("Motherboard instalada no gabinete.");
  }

  private void instalarCPU() {
    config.getHardwareController().instalarCPU(config.getHardwareFactory().criarCpuPadrao());
    config.getHardwareView().sucesso("CPU instalada no socket da motherboard.");
  }

  private void instalarRAM() {
    config.getHardwareController().instalarRAM(config.getHardwareFactory().criarRamPadrao());
    config.getHardwareView().sucesso("Modulo RAM instalado no proximo slot livre.");
  }

  private void instalarGPU() {
    config.getHardwareController().instalarGPU(config.getHardwareFactory().criarGpuPadrao());
    config.getHardwareView().sucesso("GPU instalada no slot PCIe.");
  }

  private void instalarSSD() {
    config.getHardwareController().instalarSSD(config.getHardwareFactory().criarSsdComSistema());
    config.getHardwareView().sucesso("SSD M.2 com sistema operativo instalado.");
  }

  private void mostrarComputador() {
    config.getComputadorView().mostrar(computador, config.getHardwareService());
  }

  private void ligar() {
    config.getComputadorController().executar(new LigarComputadorCommand(config.getBootService(), computador));
  }

  private void desligar() {
    config.getComputadorController().executar(new DesligarComputadorCommand(config.getBootService(), computador));
    System.out.println("Computador desligado.");
  }

  private void instalarProgramasBase() {
    config.getProgramaController().instalar(config.getProgramaFactory().criarNavegador());
    config.getProgramaController().instalar(config.getProgramaFactory().criarEditorCodigo());
    config.getProgramaController().instalar(config.getProgramaFactory().criarJogo());
    System.out.println("Programas base instalados.");
  }

  private void executarNavegador() {
    config.getProgramaController().executar("Navegador");
    config.getProgramaView().executado("Navegador");
  }

  private void instalarDrivers() {
    config.getDriverService().instalar(new DriverGrafico());
    config.getDriverService().instalar(new DriverAudio());
    config.getDriverService().instalar(new DriverRede());
    System.out.println("Drivers essenciais instalados: " + config.getDriverService().listar().size());
  }

  private void criarFicheiroTeste() {
    config.getFicheiroService().criarFicheiro(computador, "/utilizadores", "notas.txt",
        "Primeiro ficheiro criado no simulador.");
    System.out.println("Ficheiro criado em /utilizadores.");
  }

  private void testarRede() {
    String resultado = config.getRedeService().conectar(
        new PlacaRede("AA:BB:CC:DD:EE:FF", 1000),
        new PortaEthernet());
    System.out.println(resultado);
  }

  private void ativarSeguranca() {
    Firewall firewall = new Firewall();
    Antivirus antivirus = new Antivirus("Java Defender");
    config.getSegurancaService().ativarProtecoes(firewall, antivirus);
    System.out.println("Firewall ativo: " + firewall.isAtivo()
        + " | Antivirus ativo: " + antivirus.isProtecaoAtiva());
  }

  private void diagnostico() {
    System.out.println(config.getDiagnosticoService().gerarRelatorio(computador));
  }

  private void usarPulseiraAntiestatica() {
    config.getHardwareService().descarregarEstaticidade(computador);
    System.out.println("Pulseira antiestatica activa. Pode manusear RAM e GPU com seguranca.");
  }

  private void instalarEspacadores() {
    config.getHardwareService().instalarEspacadores(computador);
    System.out.println("Parafusos espacadores instalados no gabinete.");
  }

  private void aplicarPastaTermica() {
    config.getHardwareService().aplicarPastaTermica(computador);
    System.out.println("Pasta termica aplicada entre CPU e cooler.");
  }

  private void ligarCaboEps() {
    config.getHardwareService().ligarCaboEpsCpu(computador);
    System.out.println("Cabo EPS de 8 pinos do CPU ligado.");
  }

  private void ligarCaboPcie() {
    config.getHardwareService().ligarCaboPcieGpu(computador);
    System.out.println("Cabo PCIe suplementar da GPU ligado.");
  }

  private void executarJogo3D() {
    config.getProgramaController().executar("Jogo 3D");
    config.getHardwareService().simularTick(computador);
    System.out.println("Jogo 3D em execucao. Consumo e temperatura aumentaram.");
  }

  private void renderizarBsod(String codigo, String mensagem) {
    for (int i = 0; i < 30; i++) {
      System.out.println();
    }
    System.out.println("============================================================");
    System.out.println("                       BLUE SCREEN");
    System.out.println("============================================================");
    System.out.println(":(");
    System.out.println("O PC encontrou um problema fatal e foi desligado.");
    System.out.println();
    System.out.println("STOP CODE: " + codigo);
    System.out.println(mensagem);
    System.out.println();
    System.out.println("Verifique montagem mecanica, energia, temperatura, RAM e GPU.");
    System.out.println("============================================================");
  }
}
