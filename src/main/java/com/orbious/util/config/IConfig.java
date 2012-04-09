package com.orbious.util.config;

public interface IConfig {

  public boolean isString();
  public String asString();

  public boolean isInt();
  public int asInt();

  public boolean isFloat();
  public float asFloat();

  public boolean isDouble();
  public double asDouble();

  public boolean isLong();
  public long asLong();

  public boolean isBool();
  public boolean asBool();

  public String getName();

}
