package com.orbious.util;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class HashSets {

  /**
   * Convert the contents of a <code>String</code> to a
   * <code>HashSet</code> separated on <code>Character</code> boundaries.
   *
   * @param str   The <code>String</code> to interrogate.
   *
   * @return    A <code>HashSet</code> containing the contents of the
   *            <code>String</code> <code>str</code>.
   */
  public static HashSet<Character> cvtStringToHashSet(String str) {
    char[] buf = str.toCharArray();
    HashSet<Character> hs = new HashSet<Character>();

    for ( int i = 0; i < buf.length; i++ )
      hs.add(buf[i]);

    return hs;
  }

  /**
   *
   */
  public static HashSet<Integer> cvtListToHashSet(List<Integer> list) {
    HashSet<Integer> hs = new HashSet<Integer>();
    for ( int i = 0; i < list.size(); i++ )
      hs.add(list.get(i));

    return hs;
  }

  /**
   * Converts the contents of the file <code>filename</code> to
   * a <code>HashSet</code> of <code>String</code>' which each line in the
   * file occupying an entry in the <code>HashSet</code>.
   *
   * @param filename    The absolute filename to parse.
   * @param lowercase   Convert text to lowercase before adding to <code>HashSet</code>.
   * @return    A <code>HashSet</code> containing the contents
   *            of <code>filename</code>.
   */
  public static HashSet<String> cvtFileToHashSet(String filename, boolean lowercase)
    throws FileNotFoundException, IOException {
    InputStream in = Resources.getResourceStream(new File(filename));
    if ( in == null )
      throw new FileNotFoundException("Failed to open file " + filename);

    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    HashSet<String> hs = new HashSet<String>();

    String wd;
    while ( (wd = br.readLine()) != null ) {
      if ( !wd.matches("#.*") ) {
        // ignore comments.
        if ( lowercase )
          hs.add(wd.toLowerCase());
        else
          hs.add(wd);
      }
    }

    br.close();
    return(hs);
  }
}
