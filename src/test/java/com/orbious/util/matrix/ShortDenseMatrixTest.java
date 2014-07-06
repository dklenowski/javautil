package com.orbious.util.matrix;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class ShortDenseMatrixTest {

  @Test
  public void getset_Valid() throws Exception {
    ShortDenseMatrix matrix = new ShortDenseMatrix(10);

    matrix.set(0, 0, (short)1);
    matrix.set(0, 1, (short)1);
    matrix.set(1, 0, (short)9);
    matrix.set(1, 1, (short)2);

    assertThat(matrix.get(0, 0), is(equalTo((short)1)));
    assertThat(matrix.get(0, 1), is(equalTo((short)1)));
    assertThat(matrix.get(1, 0), is(equalTo((short)9)));
    assertThat(matrix.get(1, 1), is(equalTo((short)2)));

    assertThat(matrix.get(2, 2), is(equalTo((short)0)));
  }

  @Test
  public void getset_Invalid() throws Exception {
    ShortDenseMatrix matrix = new ShortDenseMatrix(10);

    assertThat(matrix.get(0, 0), is(equalTo((short)0)));
    assertThat(matrix.get(0, 1), is(equalTo((short)0)));
    assertThat(matrix.get(1, 0), is(equalTo((short)0)));
    assertThat(matrix.get(1, 1), is(equalTo((short)0)));
    assertThat(matrix.get(2, 2), is(equalTo((short)0)));
  }
}
