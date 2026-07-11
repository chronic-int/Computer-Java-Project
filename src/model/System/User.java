package model.System;

public class User {
  private final String username;
  private final boolean administrator;

  public User() {
    this("user", false);
  }

  public User(String username, boolean administrator) {
    this.username = username;
    this.administrator = administrator;
  }

  public String getUsername() {
    return username;
  }

  public boolean isAdministrator() {
    return administrator;
  }
}
