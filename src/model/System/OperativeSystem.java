package model.System;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OperativeSystem {
  private final String name;
  private String version;
  private final List<Process> processes;

  public OperativeSystem() {
    this("JavaOS", "1.0");
  }

  public OperativeSystem(String name, String version) {
    this.name = name;
    this.version = version;
    this.processes = new ArrayList<>();
  }

  public Process startProcess(String processName) {
    Process process = new Process(processName);
    processes.add(process);
    return process;
  }

  public void update(String version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public String getVersion() {
    return version;
  }

  public List<Process> getProcesses() {
    return Collections.unmodifiableList(processes);
  }
}
