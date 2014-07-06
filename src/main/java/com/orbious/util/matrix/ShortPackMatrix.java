package com.orbious.util.matrix;

import com.orbious.util.IntPacker;

import gnu.trove.iterator.TLongShortIterator;
import gnu.trove.map.hash.TLongShortHashMap;

public class ShortPackMatrix {

  protected TLongShortHashMap data;

  public ShortPackMatrix() {
    data = new TLongShortHashMap();
  }

  public int size() {
    return data.size();
  }

  public void add(int row, int column, short value) {
    short s;
    long k;

    k = IntPacker.pack(row, column);
    s = data.get(k);

    s += value;
    data.put(k, s);
  }

  // we add one to distinguish results that have not been added
  // since the default (undefined) value form TLongShortHashMap is 0.
  public void set(int row, int column, short value) {
    data.put(IntPacker.pack(row, column), (short)(value+(short)1));
  }

  public short get(int row, int column) {
    short value = data.get(IntPacker.pack(row, column));
    return (short)(value-(short)1);
  }

  @Override
  public String toString() {
    StringBuilder sb;
    long key;
    short value;
    TLongShortIterator it;

    sb = new StringBuilder();
    for ( it = data.iterator(); it.hasNext(); ) {
      it.advance();
      key = it.key();
      value = data.get(key);

      sb.append("[" + IntPacker.i(key) + ", " +
          IntPacker.j(key) + "]=" + (value-1) + "\n");
    }

    return sb.toString();
  }
}
