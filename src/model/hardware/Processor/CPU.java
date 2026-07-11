package model.Hardware.Processor;

public class CPU {
  private final String model;
  private final String socket;
  private final int cores;
  private final int threads;

  public CPU() {
    this("Generic CPU", "AM4", 4, 8);
  }

  public CPU(String model, String socket, int cores, int threads) {
    this.model = model;
    this.socket = socket;
    this.cores = cores;
    this.threads = threads;
  }

  public String executeInstruction(String instruction) {
    return model + " executed: " + instruction;
  }

  public String getSocket() {
    return socket;
  }

  public int getCores() {
    return cores;
  }

  public int getThreads() {
    return threads;
  }
}
