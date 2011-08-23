package com.orbious.util.config;

public class ConfigException extends Exception {

  private static final long serialVersionUID = 1L;

  public ConfigException(String msg) {
    super(msg);
  }

  public ConfigException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
