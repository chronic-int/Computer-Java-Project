package model.System;

public class Process {
  private static int nextPid = 1;

  private final int pid;
  private final String name;
  private boolean running;

  public Process() {
    this("process");
  }

  public Process(String name) {
    this.pid = nextPid++;
    this.name = name;
    this.running = true;
  }

  public void stop() {
    running = false;
  }

  public int getPid() {
    return pid;
  }

  public String getName() {
    return name;
  }

  public boolean isRunning() {
    return running;
  }
}
