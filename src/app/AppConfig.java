package app;

import controller.ComputadorController;
import controller.HardwareController;
import controller.ProgramaController;
import controller.SistemaController;
import factory.ComputadorFactory;
import factory.HardwareFactory;
import factory.ProgramaFactory;
import model.computador.Computador;
import repository.ComputadorRepository;
import repository.ProgramaRepository;
import service.BootService;
import service.DiagnosticoService;
import service.DriverService;
import service.EnergiaService;
import service.FicheiroService;
import service.HardwareService;
import service.ProgramaService;
import service.RedeService;
import service.SegurancaService;
import service.SistemaService;
import util.Constantes;
import view.ConsoleView;
import view.MenuPrincipal;
import view.Menus;
import view.computador.ComputadorView;
import view.hardware.HardwareView;
import view.software.ProgramaView;

public class AppConfig {
  private final Computador computador;
  private final HardwareFactory hardwareFactory;
  private final ProgramaFactory programaFactory;
  private final ProgramaService programaService;
  private final HardwareService hardwareService;
  private final DiagnosticoService diagnosticoService;
  private final DriverService driverService;
  private final FicheiroService ficheiroService;
  private final RedeService redeService;
  private final SegurancaService segurancaService;
  private final BootService bootService;
  private final ComputadorController computadorController;
  private final HardwareController hardwareController;
  private final ProgramaController programaController;
  private final SistemaController sistemaController;
  private final MenuPrincipal menuPrincipal;
  private final ComputadorView computadorView;
  private final HardwareView hardwareView;
  private final ProgramaView programaView;

  public AppConfig() {
    ComputadorRepository computadorRepository = new ComputadorRepository();
    ComputadorFactory computadorFactory = new ComputadorFactory();
    ProgramaRepository programaRepository = new ProgramaRepository();
    ProgramaService programaService = new ProgramaService(programaRepository);
    SistemaService sistemaService = new SistemaService();

    this.computador = computadorFactory.criarComputadorAprendizagem();
    computadorRepository.guardar(Constantes.ID_COMPUTADOR_PRINCIPAL, computador);
    this.hardwareFactory = new HardwareFactory();
    this.programaFactory = new ProgramaFactory();
    this.programaService = programaService;
    this.hardwareService = new HardwareService();
    this.diagnosticoService = new DiagnosticoService(hardwareService);
    this.driverService = new DriverService();
    this.ficheiroService = new FicheiroService();
    this.redeService = new RedeService();
    this.segurancaService = new SegurancaService();
    EnergiaService energiaService = new EnergiaService(hardwareService);
    this.bootService = new BootService(hardwareService, energiaService);
    this.computadorController = new ComputadorController(computador);
    this.hardwareController = new HardwareController(computador, hardwareService);
    this.programaController = new ProgramaController(computador, programaService);
    this.sistemaController = new SistemaController(computador, sistemaService);
    this.menuPrincipal = new MenuPrincipal(new ConsoleView(), new Menus());
    this.computadorView = new ComputadorView();
    this.hardwareView = new HardwareView();
    this.programaView = new ProgramaView();
  }

  public Computador getComputador() {
    return computador;
  }

  public HardwareFactory getHardwareFactory() {
    return hardwareFactory;
  }

  public ProgramaFactory getProgramaFactory() {
    return programaFactory;
  }

  public ProgramaService getProgramaService() {
    return programaService;
  }

  public HardwareService getHardwareService() {
    return hardwareService;
  }

  public DiagnosticoService getDiagnosticoService() {
    return diagnosticoService;
  }

  public DriverService getDriverService() {
    return driverService;
  }

  public FicheiroService getFicheiroService() {
    return ficheiroService;
  }

  public RedeService getRedeService() {
    return redeService;
  }

  public SegurancaService getSegurancaService() {
    return segurancaService;
  }

  public BootService getBootService() {
    return bootService;
  }

  public ComputadorController getComputadorController() {
    return computadorController;
  }

  public HardwareController getHardwareController() {
    return hardwareController;
  }

  public ProgramaController getProgramaController() {
    return programaController;
  }

  public SistemaController getSistemaController() {
    return sistemaController;
  }

  public MenuPrincipal getMenuPrincipal() {
    return menuPrincipal;
  }

  public ComputadorView getComputadorView() {
    return computadorView;
  }

  public HardwareView getHardwareView() {
    return hardwareView;
  }

  public ProgramaView getProgramaView() {
    return programaView;
  }
}
