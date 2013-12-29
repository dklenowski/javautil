package com.orbious.util;

public class Defect extends RuntimeException {

  private static final long serialVersionUID = -6831411343170951809L;

  public Defect(String msg) {
    super(msg);
  }
  
  public Defect(String msg, Throwable cause) {
    super(msg, cause);
  }
}
