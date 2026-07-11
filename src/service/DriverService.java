package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.drivers.Driver;

public class DriverService {
  private final List<Driver> drivers = new ArrayList<>();

  public void instalar(Driver driver) {
    drivers.add(driver);
  }

  public void atualizarTodos(String versao) {
    for (Driver driver : drivers) {
      driver.atualizar(versao);
    }
  }

  public List<Driver> listar() {
    return Collections.unmodifiableList(drivers);
  }
}
