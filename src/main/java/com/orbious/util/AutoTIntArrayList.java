package com.orbious.util;

import gnu.trove.list.array.TIntArrayList;

public class AutoTIntArrayList extends TIntArrayList {
  
  public AutoTIntArrayList() { 
    super();
  }
  
  public void add(int idx, int val) {
    if ( size() > idx ) {
      set(idx, val);
    } else {
      // size() <= idx
      fill(size(), idx, -1);
      insert(idx, val);
    }
  }
}
