package com.orbious.util;

import java.io.Serializable;
import java.util.Arrays;

public final class ArrayEntry implements Serializable {
  
  private static final long serialVersionUID = 1L;
  public final int[] entry;
  
  public ArrayEntry(int[] entry) {
    this.entry = entry;
  }
  
  public int[] entry() {
    return entry.clone();
  }
  
  @Override
  public String toString() {
    return Strings.cvtIntArray(entry);
  }
  
  @Override
  public boolean equals(Object obj) {
    if ( !(obj instanceof ArrayEntry) )
      return false;

    return Arrays.equals(entry, ((ArrayEntry)obj).entry);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(entry);
  }
}
