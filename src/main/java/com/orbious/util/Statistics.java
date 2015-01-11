package com.orbious.util;

import org.apache.log4j.Logger;

import com.orbious.util.Loggers;
import com.orbious.util.Strings;

import gnu.trove.list.array.TIntArrayList;

public class Statistics {

  private int[] data;
  private double sum = Double.NaN;
  private double mean = Double.NaN;
  private double stddev = Double.NaN;
  
  protected static final Logger logger = Loggers.logger();
  
  public Statistics(int[] data) { this.data = data; };
  public Statistics() { }
  
  public void set(int[] data) {
    this.data = data;
    sum = Double.NaN;
    mean = Double.NaN;
    stddev = Double.NaN;
  }
  
  public double mean() {
    if ( !Double.isNaN(mean) ) return mean;

    sum();
    mean = sum/(double)data.length;
    return mean;
  }
  
  public double stddev() {
    if ( !Double.isNaN(stddev) ) return stddev;
    
    mean();
    double tmp = 0;
    for ( int i = 0; i < data.length; i++ ) 
      tmp += Math.pow( ((double)data[i]-mean), 2 );
    
    stddev = Math.sqrt( tmp/(double)(data.length-1) );
    return stddev;
  }
  
  public double sum() {
    if ( !Double.isNaN(sum) ) return sum;
    
    sum = 0;
    for ( int i = 0; i < data.length; i++ ) 
      sum += (double)data[i];
    
    return sum;
  }

  public int[] removeusingzscore(double zscore) { 
    TIntArrayList al = new TIntArrayList();

    stddev();
    
    double score;
    for ( int i = 0; i < data.length; i++ ) {
      score = Math.abs((double)data[i]-mean)/stddev;
      logger.debug("data[" + i + "]=" + data[i] + " score=" + score);
      if ( score < zscore ) al.add(data[i]);
    }
    
    
    int[] ret = al.toArray();
    logger.info("Zscore=" + zscore + 
        " Before=" + Strings.cvtIntArray(data) + " After=" + Strings.cvtIntArray(ret));
    
    return al.toArray();
  }
  
  
}
