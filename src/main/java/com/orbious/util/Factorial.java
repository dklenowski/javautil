package com.orbious.util;

public class Factorial {

  /** 
   * FROM: http://grepcode.com/file_/repo1.maven.org/maven2/org.apache.commons/commons-math/2.1/org/apache/commons/math/util/MathUtils.java/?v=source
   */
  
    /** All long-representable factorials */
    private static final long[] FACTORIALS = new long[] {
                       1l,                  1l,                   2l,
                       6l,                 24l,                 120l,
                     720l,               5040l,               40320l,
                  362880l,            3628800l,            39916800l,
               479001600l,         6227020800l,         87178291200l,
           1307674368000l,     20922789888000l,     355687428096000l,
        6402373705728000l, 121645100408832000l, 2432902008176640000l };
    
  private Factorial() { }
  
  /**
   * Returns n!. Shorthand for <code>n</code> <a
   * href="http://mathworld.wolfram.com/Factorial.html"> Factorial</a>, the
   * product of the numbers <code>1,...,n</code>.
   * <p>
   * <Strong>Preconditions</strong>:
   * <ul>
   * <li> <code>n >= 0</code> (otherwise
   * <code>IllegalArgumentException</code> is thrown)</li>
   * <li> The result is small enough to fit into a <code>long</code>. The
   * largest value of <code>n</code> for which <code>n!</code> <
   * Long.MAX_VALUE</code> is 20. If the computed value exceeds <code>Long.MAX_VALUE</code>
   * an <code>ArithMeticException </code> is thrown.</li>
   * </ul>
   * </p>
   *
   * @param n argument
   * @return <code>n!</code>
   * @throws ArithmeticException if the result is too large to be represented
   *         by a long integer.
   * @throws IllegalArgumentException if n < 0
   */
  public static long factorial(final int n) {
    if (n < 0) {
      throw new IllegalArgumentException(
          "must have n >= 0 for n!, got n = {0}");
    }
    if (n > 20) {
      throw new ArithmeticException(
         "factorial value is too large to fit in a long");
    }

    return FACTORIALS[n];
  }
}
