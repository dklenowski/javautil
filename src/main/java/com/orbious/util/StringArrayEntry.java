package com.orbious.util;

import java.io.Serializable;
import java.util.Arrays;

public final class StringArrayEntry implements Serializable, Comparable<StringArrayEntry> {
  
  private static final long serialVersionUID = 1L;
  private final String[] entry;
  
  public StringArrayEntry(String[] entry) {
    this.entry = entry;
  }
  
  public String[] entry() {
    return entry.clone();
  }
  
  @Override
  public String toString() {
    return Strings.cvtStringArray(entry);
  }
  
  @Override
  public boolean equals(Object obj) {
    if ( !(obj instanceof StringArrayEntry) )
      return false;

    return Arrays.equals(entry, ((StringArrayEntry)obj).entry);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(entry);
  }

  @Override
  public int compareTo(StringArrayEntry o) {
    int length1 = this.entry.length;
    int length2 = o.entry.length;
    int min = Math.min(length1, length2);
    
    for ( int i = 0; i < min; i++ ) {
      if ( this.entry[i] != o.entry[i] ) 
        return this.entry[i].compareTo( o.entry[i] );
    }
    
    return length1-length2;
  }
}
