package model.Software;

public class CodeEditor extends Program {
  public CodeEditor() {
    super("Code Editor", "1.0");
  }

  public String openProject(String projectName) {
    run();
    return "Project opened: " + projectName;
  }
}
