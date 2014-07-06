package com.orbious.util.matrix;

public class ShortDenseMatrix {

  protected int num;
  protected short[] data;

  public ShortDenseMatrix(int n) {
    if ( n <= 0 )
      throw new IndexOutOfBoundsException("Matrix size cannot be less than 0");

    num = n;
    data = new short[num*num];
  }

  public void add(int row, int column, short value) {
    data[getIndex(row, column)] += value;
  }

  public void fadd(int row, int column, short value) {
    data[row + column * num] += value;
  }

  public void set(int row, int column, short value) {
    data[getIndex(row, column)] = value;
  }

  public void fset(int row, int column, short value) {
    data[row + column * num] = value;
  }

  public short get(int row, int column) {
    return data[getIndex(row, column)];
  }

  public short fget(int row, int column) {
    return data[row + column * num];
  }

  private int getIndex(int row, int column) {
    check(row, column);
    return row + column * num;
  }

  private void check(int row, int column) {
    if ( row < 0 )
      throw new IndexOutOfBoundsException("row index negative (" + row + ")");
    else if ( column < 0 )
      throw new IndexOutOfBoundsException("column index negative (" + column + ")");
    else if ( row >= num )
      throw new IndexOutOfBoundsException("row index >= numRows (" + row + " >= " + num + ")");
    else if ( column >= num )
      throw new IndexOutOfBoundsException("column index >= numColumns (" + column + " >= " + num + ")");
  }
}
