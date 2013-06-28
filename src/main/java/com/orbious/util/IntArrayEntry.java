package com.orbious.util;

import java.io.Serializable;
import java.util.Arrays;

public final class IntArrayEntry implements Serializable, Comparable<IntArrayEntry> {
  
  private static final long serialVersionUID = 1L;
  private final int[] entry;
  
  public IntArrayEntry(int[] entry) {
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
    if ( !(obj instanceof IntArrayEntry) )
      return false;

    return Arrays.equals(entry, ((IntArrayEntry)obj).entry);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(entry);
  }

  @Override
  public int compareTo(IntArrayEntry o) {
    int length1 = this.entry.length;
    int length2 = o.entry.length;
    int min = Math.min(length1, length2);
    
    for ( int i = 0; i < min; i++ ) {
      if ( this.entry[i] != o.entry[i] ) 
        return this.entry[i] - o.entry[i];
    }
    
    return length1-length2;
  }
}
