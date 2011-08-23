package com.orbious.util.config;

public interface IConfig {

  public boolean isString();
  public String asString();

  public boolean isInt();
  public int asInt();

  public boolean isDouble();
  public double asDouble();

  public String getName();

}
