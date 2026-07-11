package app;

import exception.BootException;
import exception.HardwareException;
import exception.ProgramaException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import model.computador.Computador;
import model.enums.SistemaOperativoTipo;
import model.enums.TipoDDR;
import model.enums.TipoMotherboard;
import model.enums.TipoSocket;
import model.ficheiros.Ficheiro;
import model.ficheiros.Pasta;
import model.hardware.armazenamento.SSD;
import model.hardware.energia.Fonte;
import model.hardware.gabinete.Gabinete;
import model.hardware.grafico.GPU;
import model.hardware.memoria.RAM;
import model.hardware.motherboard.Motherboard;
import model.hardware.processamento.CPU;
import model.sistema.BIOS;
import model.sistema.Processo;
import model.software.Programa;

public class ComputadorRealistaApp extends JFrame {
  private final AppConfig config;
  private final Computador computador;
  private final DefaultListModel<String> componentesModel;
  private final DefaultListModel<String> programasModel;
  private final DefaultListModel<String> processosModel;
  private final JTextArea consola;
  private final JTextArea ficheirosArea;
  private final JLabel estadoLabel;
  private final JLabel consumoLabel;
  private final JLabel cpuLabel;
  private JComboBox<OpcaoComponente<Gabinete>> gabineteCombo;
  private JComboBox<OpcaoComponente<Fonte>> fonteCombo;
  private JComboBox<OpcaoComponente<Motherboard>> motherboardCombo;
  private JComboBox<OpcaoComponente<CPU>> cpuCombo;
  private JComboBox<OpcaoComponente<RAM>> ramCombo;
  private JComboBox<OpcaoComponente<GPU>> gpuCombo;
  private JComboBox<OpcaoComponente<SSD>> ssdCombo;
  private JComboBox<SistemaOperativoTipo> sistemaCombo;

  public static void iniciar() {
    SwingUtilities.invokeLater(() -> new ComputadorRealistaApp().setVisible(true));
  }

  public ComputadorRealistaApp() {
    super("Simulador Realista de Computador");
    this.config = new AppConfig();
    this.computador = config.getComputador();
    this.componentesModel = new DefaultListModel<>();
    this.programasModel = new DefaultListModel<>();
    this.processosModel = new DefaultListModel<>();
    this.consola = new JTextArea();
    this.ficheirosArea = new JTextArea();
    this.estadoLabel = new JLabel();
    this.consumoLabel = new JLabel();
    this.cpuLabel = new JLabel();

    configurarJanela();
    construirInterface();
    instalarTemporizadorSistema();
    actualizarTudo();
    log("Aplicacao iniciada. Monte o computador, instale um sistema operativo e carregue em Start.");
  }

  private void configurarJanela() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(1100, 720));
    setLocationRelativeTo(null);
  }

  private void construirInterface() {
    JPanel topo = new JPanel(new BorderLayout());
    topo.setBorder(BorderFactory.createEmptyBorder(12, 14, 8, 14));
    JLabel titulo = new JLabel("Oficina de montagem e arranque");
    titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
    JPanel estados = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
    estados.add(estadoLabel);
    estados.add(consumoLabel);
    estados.add(cpuLabel);
    topo.add(titulo, BorderLayout.WEST);
    topo.add(estados, BorderLayout.EAST);

    JTabbedPane tabs = new JTabbedPane();
    tabs.add("Montagem", criarPainelMontagem());
    tabs.add("Sistema", criarPainelSistema());
    tabs.add("Ficheiros", criarPainelFicheiros());
    tabs.add("Diagnostico", criarPainelDiagnostico());

    consola.setEditable(false);
    consola.setLineWrap(true);
    consola.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
    JScrollPane consolaScroll = new JScrollPane(consola);
    consolaScroll.setPreferredSize(new Dimension(1000, 160));
    consolaScroll.setBorder(BorderFactory.createTitledBorder("Consola do computador"));

    add(topo, BorderLayout.NORTH);
    add(tabs, BorderLayout.CENTER);
    add(consolaScroll, BorderLayout.SOUTH);
  }

  private JPanel criarPainelMontagem() {
    JPanel painel = new JPanel(new BorderLayout(12, 12));
    painel.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

    JPanel formulario = new JPanel(new GridBagLayout());
    gabineteCombo = combo(catalogoGabinetes());
    fonteCombo = combo(catalogoFontes());
    motherboardCombo = combo(catalogoMotherboards());
    cpuCombo = combo(catalogoCPUs());
    ramCombo = combo(catalogoRAM());
    gpuCombo = combo(catalogoGPUs());
    sistemaCombo = new JComboBox<>(SistemaOperativoTipo.values());
    sistemaCombo.setSelectedItem(SistemaOperativoTipo.LINUX);
    ssdCombo = combo(catalogoSSDs((SistemaOperativoTipo) sistemaCombo.getSelectedItem()));
    sistemaCombo.addActionListener(e -> ssdCombo.setModel(modelo(catalogoSSDs(
        (SistemaOperativoTipo) sistemaCombo.getSelectedItem()))));

    adicionarLinha(formulario, 0, "Gabinete", gabineteCombo, botao("Instalar", () -> instalar(() ->
        config.getHardwareController().instalarGabinete(item(gabineteCombo)), "Gabinete instalado.")));
    adicionarLinha(formulario, 1, "Fonte", fonteCombo, botao("Instalar", () -> instalar(() ->
        config.getHardwareController().instalarFonte(item(fonteCombo)), "Fonte instalada.")));
    adicionarLinha(formulario, 2, "Motherboard", motherboardCombo, botao("Instalar", () -> instalar(() ->
        config.getHardwareController().instalarMotherboard(item(motherboardCombo)), "Motherboard instalada.")));
    adicionarLinha(formulario, 3, "CPU", cpuCombo, botao("Instalar", () -> instalar(() ->
        config.getHardwareController().instalarCPU(item(cpuCombo)), "CPU instalada.")));
    adicionarLinha(formulario, 4, "RAM", ramCombo, botao("Instalar", () -> instalar(() ->
        config.getHardwareController().instalarRAM(item(ramCombo)), "RAM instalada.")));
    adicionarLinha(formulario, 5, "GPU", gpuCombo, botao("Instalar", () -> instalar(() ->
        config.getHardwareController().instalarGPU(item(gpuCombo)), "GPU instalada.")));
    adicionarLinha(formulario, 6, "Sistema operativo", sistemaCombo, new JLabel("Escolha antes do SSD"));
    adicionarLinha(formulario, 7, "SSD", ssdCombo, botao("Instalar", () -> instalar(() ->
        config.getHardwareController().instalarSSD(item(ssdCombo)), "SSD com sistema instalado.")));

    JPanel accoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
    accoes.add(botao("Montar PC recomendado", this::montarRecomendado));
    accoes.add(botao("Criar componente", this::criarComponente));
    accoes.add(botao("Start", this::ligarComputador));
    accoes.add(botao("Desligar", this::desligarComputador));

    JPanel esquerda = new JPanel(new BorderLayout(8, 8));
    esquerda.add(formulario, BorderLayout.CENTER);
    esquerda.add(accoes, BorderLayout.SOUTH);

    JList<String> lista = new JList<>(componentesModel);
    JScrollPane scroll = new JScrollPane(lista);
    scroll.setBorder(BorderFactory.createTitledBorder("Componentes instalados"));

    painel.add(esquerda, BorderLayout.CENTER);
    painel.add(scroll, BorderLayout.EAST);
    return painel;
  }

  private JPanel criarPainelSistema() {
    JPanel painel = new JPanel(new BorderLayout(12, 12));
    painel.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));

    JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
    botoes.add(botao("Instalar software base", this::instalarSoftwareBase));
    botoes.add(botao("Instalar Terminal", () -> instalarPrograma(config.getProgramaFactory().criarTerminal())));
    botoes.add(botao("Executar seleccionado", this::executarProgramaSeleccionado));
    botoes.add(botao("Simular CPU", this::simularCPU));

    JList<String> programas = new JList<>(programasModel);
    JScrollPane programasScroll = new JScrollPane(programas);
    programasScroll.setBorder(BorderFactory.createTitledBorder("Software instalado"));
    programas.putClientProperty("programasList", Boolean.TRUE);

    JList<String> processos = new JList<>(processosModel);
    JScrollPane processosScroll = new JScrollPane(processos);
    processosScroll.setBorder(BorderFactory.createTitledBorder("Processos activos"));

    JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, programasScroll, processosScroll);
    split.setResizeWeight(0.5);
    painel.add(botoes, BorderLayout.NORTH);
    painel.add(split, BorderLayout.CENTER);
    return painel;
  }

  private JPanel criarPainelFicheiros() {
    JPanel painel = new JPanel(new BorderLayout(10, 10));
    painel.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
    JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
    botoes.add(botao("Criar pasta", this::criarPasta));
    botoes.add(botao("Criar ficheiro", this::criarFicheiro));
    ficheirosArea.setEditable(false);
    ficheirosArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
    painel.add(botoes, BorderLayout.NORTH);
    painel.add(new JScrollPane(ficheirosArea), BorderLayout.CENTER);
    return painel;
  }

  private JPanel criarPainelDiagnostico() {
    JPanel painel = new JPanel(new BorderLayout(10, 10));
    painel.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
    JTextArea area = new JTextArea();
    area.setEditable(false);
    area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
    JButton actualizar = botao("Actualizar diagnostico", () -> {
      area.setText(config.getDiagnosticoService().gerarRelatorio(computador));
      area.append(System.lineSeparator() + estadoDetalhado());
    });
    painel.add(actualizar, BorderLayout.NORTH);
    painel.add(new JScrollPane(area), BorderLayout.CENTER);
    return painel;
  }

  private void montarRecomendado() {
    instalar(() -> config.getHardwareController().instalarGabinete(item(gabineteCombo)), "Gabinete instalado.");
    instalar(() -> config.getHardwareController().instalarFonte(item(fonteCombo)), "Fonte instalada.");
    instalar(() -> config.getHardwareController().instalarMotherboard(item(motherboardCombo)), "Motherboard instalada.");
    instalar(() -> config.getHardwareController().instalarCPU(item(cpuCombo)), "CPU instalada.");
    instalar(() -> config.getHardwareController().instalarRAM(item(ramCombo)), "RAM instalada.");
    instalar(() -> config.getHardwareController().instalarGPU(item(gpuCombo)), "GPU instalada.");
    instalar(() -> config.getHardwareController().instalarSSD(item(ssdCombo)), "SSD instalado.");
  }

  private void ligarComputador() {
    try {
      String resultado = config.getBootService().ligar(computador);
      log(resultado);
      arrancarSistemaOperativo();
    } catch (BootException | HardwareException ex) {
      log("Falha no arranque: " + ex.getMessage());
    }
    actualizarTudo();
  }

  private void desligarComputador() {
    config.getBootService().desligar(computador);
    log("Computador desligado.");
    actualizarTudo();
  }

  private void arrancarSistemaOperativo() {
    if (computador.getSistemaOperativo() == null) {
      return;
    }
    CPU cpu = obterCpu();
    if (cpu != null) {
      for (String linha : cpu.executarPrograma(Arrays.asList("POST", "LOAD_KERNEL", "START_SERVICES", "OPEN_DESKTOP"))) {
        log(linha);
      }
    }
    computador.getSistemaOperativo().iniciarProcesso("kernel", 256);
    computador.getSistemaOperativo().iniciarProcesso("desktop", 320);
    log("Ambiente " + computador.getSistemaOperativo().getNome() + " pronto.");
  }

  private void instalarSoftwareBase() {
    instalarPrograma(config.getProgramaFactory().criarNavegador());
    instalarPrograma(config.getProgramaFactory().criarEditorCodigo());
    instalarPrograma(config.getProgramaFactory().criarJogo());
    instalarPrograma(config.getProgramaFactory().criarGestorFicheiros());
    instalarPrograma(config.getProgramaFactory().criarLojaSoftware());
  }

  private void instalarPrograma(Programa programa) {
    config.getProgramaController().instalar(programa);
    log("Software instalado: " + programa.getNome() + " " + programa.getVersao());
    actualizarTudo();
  }

  private void executarProgramaSeleccionado() {
    String nome = pedirTexto("Programa a executar", "Nome do programa:");
    if (nome == null || nome.trim().isEmpty()) {
      return;
    }
    try {
      config.getProgramaController().executar(nome.trim());
      CPU cpu = obterCpu();
      if (cpu != null) {
        log(cpu.processarInstrucao("EXEC " + nome.trim()));
      }
      log("Programa em execucao: " + nome.trim());
    } catch (ProgramaException ex) {
      log("Nao foi possivel executar: " + ex.getMessage());
    }
    actualizarTudo();
  }

  private void simularCPU() {
    CPU cpu = obterCpu();
    if (cpu == null) {
      log("Instale uma CPU antes de simular processamento.");
      return;
    }
    for (String linha : cpu.executarPrograma(Arrays.asList("FETCH", "DECODE", "ALLOCATE_RAM", "DRAW_FRAME", "WRITE_DISK"))) {
      log(linha);
    }
    actualizarTudo();
  }

  private void criarPasta() {
    String caminho = pedirTexto("Nova pasta", "Caminho da pasta, por exemplo /utilizadores/aluno:");
    if (caminho == null || caminho.trim().isEmpty()) {
      return;
    }
    computador.getSistemaFicheiros().criarPasta(normalizarCaminho(caminho));
    log("Pasta criada: " + normalizarCaminho(caminho));
    actualizarTudo();
  }

  private void criarFicheiro() {
    String pasta = pedirTexto("Pasta", "Pasta onde criar o ficheiro:");
    if (pasta == null || pasta.trim().isEmpty()) {
      return;
    }
    String nome = pedirTexto("Ficheiro", "Nome do ficheiro:");
    if (nome == null || nome.trim().isEmpty()) {
      return;
    }
    String conteudo = pedirTexto("Conteudo", "Conteudo:");
    Pasta destino = computador.getSistemaFicheiros().obterPasta(normalizarCaminho(pasta));
    if (destino == null) {
      destino = computador.getSistemaFicheiros().criarPasta(normalizarCaminho(pasta));
    }
    destino.adicionar(new Ficheiro(nome.trim(), conteudo == null ? "" : conteudo));
    log("Ficheiro criado: " + normalizarCaminho(pasta) + "/" + nome.trim());
    actualizarTudo();
  }

  private void criarComponente() {
    String[] opcoes = {"CPU", "RAM", "GPU", "Fonte", "SSD"};
    String tipo = (String) JOptionPane.showInputDialog(this, "Tipo de componente:", "Criar componente",
        JOptionPane.PLAIN_MESSAGE, null, opcoes, opcoes[0]);
    if (tipo == null) {
      return;
    }
    try {
      if ("CPU".equals(tipo)) {
        CPU cpu = new CPU(pedirTexto("CPU", "Marca:"), pedirTexto("CPU", "Modelo:"),
            TipoSocket.AM5, inteiro("Nucleos:", 8), inteiro("Threads:", 16), inteiro("TDP watts:", 95));
        adicionar(cpuCombo, new OpcaoComponente<>(cpu.getNome(), cpu));
      } else if ("RAM".equals(tipo)) {
        RAM ram = new RAM(pedirTexto("RAM", "Marca:"), inteiro("Capacidade GB:", 32), TipoDDR.DDR5,
            inteiro("Frequencia MHz:", 6000));
        adicionar(ramCombo, new OpcaoComponente<>(ram.getDescricao(), ram));
      } else if ("GPU".equals(tipo)) {
        GPU gpu = new GPU(pedirTexto("GPU", "Marca:"), pedirTexto("GPU", "Modelo:"),
            inteiro("Memoria GB:", 12), inteiro("Consumo watts:", 180));
        adicionar(gpuCombo, new OpcaoComponente<>(gpu.getDescricao(), gpu));
      } else if ("Fonte".equals(tipo)) {
        Fonte fonte = new Fonte(pedirTexto("Fonte", "Marca:"), inteiro("Potencia watts:", 750),
            pedirTexto("Fonte", "Certificacao:"));
        adicionar(fonteCombo, new OpcaoComponente<>(fonte.getDescricao(), fonte));
      } else if ("SSD".equals(tipo)) {
        SSD ssd = new SSD(pedirTexto("SSD", "Marca:"), pedirTexto("SSD", "Modelo:"),
            inteiro("Capacidade GB:", 1000), (SistemaOperativoTipo) sistemaCombo.getSelectedItem());
        adicionar(ssdCombo, new OpcaoComponente<>(ssd.getDescricao(), ssd));
      }
      log("Componente criado e adicionado ao catalogo.");
    } catch (NumberFormatException ex) {
      log("Componente cancelado: numero invalido.");
    }
  }

  private void instalar(Acao acao, String sucesso) {
    try {
      acao.executar();
      log(sucesso);
    } catch (HardwareException ex) {
      log("Falha de hardware: " + ex.getMessage());
    }
    actualizarTudo();
  }

  private void actualizarTudo() {
    actualizarComponentes();
    actualizarProgramas();
    actualizarProcessos();
    actualizarFicheiros();
    actualizarEstado();
  }

  private void actualizarComponentes() {
    componentesModel.clear();
    if (computador.getGabinete() != null) {
      componentesModel.addElement("Gabinete: " + computador.getGabinete().getModelo());
    }
    if (computador.getFonte() != null) {
      componentesModel.addElement("Fonte: " + computador.getFonte().getDescricao());
    }
    if (computador.getMotherboard() != null) {
      Motherboard mb = computador.getMotherboard();
      componentesModel.addElement("Motherboard: " + mb.getMarca() + " " + mb.getModelo());
      if (mb.getSocketCPU().estaOcupado()) {
        componentesModel.addElement("CPU: " + mb.getSocketCPU().getCpu().getNome());
      }
      mb.getSlotsRam().stream().filter(slot -> slot.estaOcupado())
          .forEach(slot -> componentesModel.addElement("RAM slot " + slot.getNumero() + ": " + slot.getModulo().getDescricao()));
      mb.getSlotsPCIe().stream().filter(slot -> slot.estaOcupado())
          .forEach(slot -> componentesModel.addElement("GPU: " + slot.getGpu().getDescricao()));
      mb.getSlotsM2().stream().filter(slot -> slot.estaOcupado())
          .forEach(slot -> componentesModel.addElement("SSD: " + slot.getSsd().getDescricao()));
    }
  }

  private void actualizarProgramas() {
    programasModel.clear();
    for (Programa programa : config.getProgramaService().getProgramaRepository().listar()) {
      programasModel.addElement(programa.getNome() + " " + programa.getVersao() + " - " + programa.getEstado());
    }
  }

  private void actualizarProcessos() {
    processosModel.clear();
    if (computador.getSistemaOperativo() == null) {
      return;
    }
    for (Processo processo : computador.getSistemaOperativo().getProcessos()) {
      processosModel.addElement("#" + processo.getPid() + " " + processo.getNome() + " " + processo.getMemoriaMb()
          + "MB ativo=" + processo.isAtivo());
    }
  }

  private void actualizarFicheiros() {
    StringBuilder sb = new StringBuilder();
    computador.getSistemaFicheiros().getPastas().forEach((caminho, pasta) -> {
      sb.append(caminho).append(System.lineSeparator());
      for (Ficheiro ficheiro : pasta.getFicheiros()) {
        sb.append("  - ").append(ficheiro.getNome()).append(" (")
            .append(ficheiro.getConteudo().length()).append(" caracteres)").append(System.lineSeparator());
      }
    });
    ficheirosArea.setText(sb.toString());
  }

  private void actualizarEstado() {
    estadoLabel.setText("Estado: " + computador.getEstado());
    estadoLabel.setForeground(new Color(25, 85, 25));
    try {
      consumoLabel.setText("Consumo: " + config.getHardwareService().calcularConsumoWatts(computador) + "W");
    } catch (RuntimeException ex) {
      consumoLabel.setText("Consumo: indisponivel");
    }
    CPU cpu = obterCpu();
    if (cpu == null) {
      cpuLabel.setText("CPU: ausente");
    } else {
      cpuLabel.setText(String.format("CPU: %.0f%%, %d instr.", cpu.getUtilizacaoPercentagem(), cpu.getInstrucoesExecutadas()));
    }
  }

  private String estadoDetalhado() {
    String so = computador.getSistemaOperativo() == null ? "Nenhum" : computador.getSistemaOperativo().getNome()
        + " " + computador.getSistemaOperativo().getVersao();
    return "Sistema operativo: " + so + System.lineSeparator()
        + "Pastas: " + computador.getSistemaFicheiros().getPastas().size() + System.lineSeparator()
        + "Programas instalados: " + config.getProgramaService().getProgramaRepository().listar().size();
  }

  private CPU obterCpu() {
    if (computador.getMotherboard() == null || !computador.getMotherboard().getSocketCPU().estaOcupado()) {
      return null;
    }
    return computador.getMotherboard().getSocketCPU().getCpu();
  }

  private void instalarTemporizadorSistema() {
    new Timer(1500, e -> actualizarEstado()).start();
  }

  private void log(String mensagem) {
    consola.append("> " + mensagem + System.lineSeparator());
    consola.setCaretPosition(consola.getDocument().getLength());
  }

  private JButton botao(String texto, Runnable acao) {
    JButton botao = new JButton(texto);
    botao.addActionListener(e -> acao.run());
    return botao;
  }

  private <T> JComboBox<OpcaoComponente<T>> combo(List<OpcaoComponente<T>> opcoes) {
    return new JComboBox<>(modelo(opcoes));
  }

  private <T> DefaultComboBoxModel<OpcaoComponente<T>> modelo(List<OpcaoComponente<T>> opcoes) {
    DefaultComboBoxModel<OpcaoComponente<T>> modelo = new DefaultComboBoxModel<>();
    for (OpcaoComponente<T> opcao : opcoes) {
      modelo.addElement(opcao);
    }
    return modelo;
  }

  @SuppressWarnings("unchecked")
  private <T> T item(JComboBox<OpcaoComponente<T>> combo) {
    return ((OpcaoComponente<T>) combo.getSelectedItem()).valor;
  }

  private <T> void adicionar(JComboBox<OpcaoComponente<T>> combo, OpcaoComponente<T> opcao) {
    combo.addItem(opcao);
    combo.setSelectedItem(opcao);
  }

  private void adicionarLinha(JPanel painel, int linha, String nome, java.awt.Component campo, java.awt.Component accao) {
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(6, 4, 6, 4);
    c.gridy = linha;
    c.gridx = 0;
    c.anchor = GridBagConstraints.WEST;
    painel.add(new JLabel(nome), c);
    c.gridx = 1;
    c.weightx = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    painel.add(campo, c);
    c.gridx = 2;
    c.weightx = 0;
    c.fill = GridBagConstraints.NONE;
    painel.add(accao, c);
  }

  private String pedirTexto(String titulo, String mensagem) {
    return JOptionPane.showInputDialog(this, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
  }

  private int inteiro(String mensagem, int padrao) {
    String valor = JOptionPane.showInputDialog(this, mensagem, String.valueOf(padrao));
    return Integer.parseInt(valor);
  }

  private String normalizarCaminho(String caminho) {
    String limpo = caminho.trim();
    return limpo.startsWith("/") ? limpo : "/" + limpo;
  }

  private List<OpcaoComponente<Gabinete>> catalogoGabinetes() {
    return Arrays.asList(
        new OpcaoComponente<>("Mid Tower ATX Airflow", new Gabinete("Mid Tower Airflow", TipoMotherboard.ATX)),
        new OpcaoComponente<>("Compacto Micro ATX", new Gabinete("Compacto Studio", TipoMotherboard.MICRO_ATX)));
  }

  private List<OpcaoComponente<Fonte>> catalogoFontes() {
    return Arrays.asList(
        new OpcaoComponente<>("Corsair 650W 80 Plus Bronze", new Fonte("Corsair", 650, "80 Plus Bronze")),
        new OpcaoComponente<>("Seasonic 850W 80 Plus Gold", new Fonte("Seasonic", 850, "80 Plus Gold")));
  }

  private List<OpcaoComponente<Motherboard>> catalogoMotherboards() {
    return Arrays.asList(
        new OpcaoComponente<>("ASUS Prime B650 ATX AM5 DDR5", new Motherboard("ASUS", "Prime B650", "MB-2026-0001",
            TipoMotherboard.ATX, TipoSocket.AM5, TipoDDR.DDR5, 4, 2, 2, new BIOS("AMI", "1.0.0"))),
        new OpcaoComponente<>("MSI B760 ATX LGA1700 DDR5", new Motherboard("MSI", "B760", "MB-2026-0002",
            TipoMotherboard.ATX, TipoSocket.LGA1700, TipoDDR.DDR5, 4, 2, 2, new BIOS("AMI", "1.2.0"))));
  }

  private List<OpcaoComponente<CPU>> catalogoCPUs() {
    return Arrays.asList(
        new OpcaoComponente<>("AMD Ryzen 5 7600 AM5", new CPU("AMD", "Ryzen 5 7600", TipoSocket.AM5, 6, 12, 65)),
        new OpcaoComponente<>("Intel Core i5-14600K LGA1700", new CPU("Intel", "Core i5-14600K", TipoSocket.LGA1700, 14, 20, 125)));
  }

  private List<OpcaoComponente<RAM>> catalogoRAM() {
    return Arrays.asList(
        new OpcaoComponente<>("Kingston Fury 16GB DDR5 5600MHz", new RAM("Kingston Fury", 16, TipoDDR.DDR5, 5600)),
        new OpcaoComponente<>("Corsair Vengeance 32GB DDR5 6000MHz", new RAM("Corsair Vengeance", 32, TipoDDR.DDR5, 6000)));
  }

  private List<OpcaoComponente<GPU>> catalogoGPUs() {
    return Arrays.asList(
        new OpcaoComponente<>("NVIDIA RTX 4060 8GB", new GPU("NVIDIA", "RTX 4060", 8, 115)),
        new OpcaoComponente<>("AMD Radeon RX 7800 XT 16GB", new GPU("AMD", "Radeon RX 7800 XT", 16, 263)));
  }

  private List<OpcaoComponente<SSD>> catalogoSSDs(SistemaOperativoTipo tipo) {
    return Arrays.asList(
        new OpcaoComponente<>("Samsung 980 1TB com " + tipo, new SSD("Samsung", "980", 1000, tipo)),
        new OpcaoComponente<>("WD Black SN850X 2TB com " + tipo, new SSD("WD Black", "SN850X", 2000, tipo)));
  }

  private interface Acao {
    void executar();
  }

  private static class OpcaoComponente<T> {
    private final String nome;
    private final T valor;

    OpcaoComponente(String nome, T valor) {
      this.nome = nome;
      this.valor = valor;
    }

    @Override
    public String toString() {
      return nome;
    }
  }
}
