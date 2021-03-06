package com.orbious.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class IntArrayEntryTest {
  
  @Test
  public void equals() throws Exception {
    IntArrayEntry ae = new IntArrayEntry(new int[] { 1, 2, 3, 4 });
    IntArrayEntry ae2 = new IntArrayEntry(new int[] { 1, 2, 3, 4 });
    
    assertThat(ae.equals(ae2), is(true));
    assertThat(ae2.equals(ae), is(true));
    assertThat(ae2.hashCode(), is(equalTo(ae.hashCode())));
    assertThat(ae.hashCode(), is(equalTo(ae2.hashCode())));
  }
  
  @Test
  public void singleequals() throws Exception {
    IntArrayEntry ae = new IntArrayEntry(new int[] { 1 });
    IntArrayEntry ae2 = new IntArrayEntry(new int[] { 1 });
    
    assertThat(ae.equals(ae2), is(true));
    assertThat(ae2.equals(ae), is(true));
    assertThat(ae2.hashCode(), is(equalTo(ae.hashCode())));
    assertThat(ae.hashCode(), is(equalTo(ae2.hashCode())));
  }
  
  @Test
  public void notequals() throws Exception {
    IntArrayEntry ae = new IntArrayEntry(new int[] { 1, 3, 4 });
    IntArrayEntry ae2 = new IntArrayEntry(new int[] { 1, 2, 3, 4 });
    
    assertThat(ae.equals(ae2), is(false));
    assertThat(ae2.equals(ae), is(false));
  }
  

  @Test
  public void notequalssingle() throws Exception {
    IntArrayEntry ae = new IntArrayEntry(new int[] { 1 });
    IntArrayEntry ae2 = new IntArrayEntry(new int[] { 2 });
    
    assertThat(ae.equals(ae2), is(false));
    assertThat(ae2.equals(ae), is(false));
  }
  
  @Test
  public void compareTo() {
    IntArrayEntry ae;
    IntArrayEntry ae2;
    
    ae = new IntArrayEntry(new int[] { 8, 9, 10, 11 });
    ae2 = new IntArrayEntry(new int[] { 8, 9, 10, 11 });
    assertThat(ae.compareTo(ae2), is(0));
    assertThat(ae2.compareTo(ae), is(0));
    
    ae = new IntArrayEntry(new int[] { 8, 9, 10 });
    ae2 = new IntArrayEntry(new int[] { 8, 9, 10, 11 });
    assertThat(ae.compareTo(ae2), is(-1));
    assertThat(ae2.compareTo(ae), is(1));

    ae = new IntArrayEntry(new int[] { 8, 9, 10, 11 });
    ae2 = new IntArrayEntry(new int[] { 8, 9, 10 });
    assertThat(ae.compareTo(ae2), is(1));
    assertThat(ae2.compareTo(ae), is(-1));
    
    ae = new IntArrayEntry(new int[] { 7, 9, 10, 11 });
    ae2 = new IntArrayEntry(new int[] { 8, 9, 10, 11 });
    assertThat(ae.compareTo(ae2), is(-1));
    assertThat(ae2.compareTo(ae), is(1));
  }
  
  
}
