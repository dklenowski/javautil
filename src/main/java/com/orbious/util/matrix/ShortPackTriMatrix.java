package com.orbious.util.matrix;

import gnu.trove.iterator.TLongShortIterator;

import com.orbious.util.IntPacker;

public class ShortPackTriMatrix extends ShortPackMatrix {

  public ShortPackTriMatrix() {
    super();
  }

  @Override
  public short get(int row, int column) {
    short value;

    value = (short)(data.get(IntPacker.pack(row, column))-(short)1);
    if ( value != -1 )
      return value;

    return (short)(data.get(IntPacker.pack(column, row))-(short)1);
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
      sb.append("[" + IntPacker.i(key) + ", " + IntPacker.j(key) + "]=" + (value-1) + "\n");
      sb.append("[" + IntPacker.j(key) + ", " + IntPacker.i(key) + "]=" + (value-1) + "\n");
    }

    return sb.toString();
  }

}
