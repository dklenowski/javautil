package com.orbious.util;

import java.io.File;
import java.io.IOException;
//import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.xml.DOMConfigurator;
import com.orbious.util.config.Config;

public class Loggers {

  // for some reason Logger.getLogger(Config.logRealm()) returns
  // true, even for uninitialised loggers?
  private static boolean initComplete = false;

  public static Logger logger() {
    init();
    return Logger.getLogger(Config.logRealm());
  }

  public static void init() {
    if ( initComplete ) {
      return;
    }
    init(new File(Config.logConfig()));
  }

  public static void init(File cfgfile) {
    Logger root = Logger.getRootLogger();

    if ( !root.getAllAppenders().hasMoreElements() ) {
      try {
        File f = Resources.getResourceFile(cfgfile);
        DOMConfigurator.configure(f.toString());
      } catch ( IOException ioe ) {
        System.err.println("Failed to find log4j resource (" + cfgfile +
            "), using BasicConfigurator.");
//        BasicConfigurator.configure();
        Logger log = Logger.getLogger(Config.logRealm());
        log.setLevel(Level.DEBUG);
        log.addAppender(new ConsoleAppender(new PatternLayout("%d{ISO8601} %-5p  %C{2} (%M:%L) - %m%n") ));
      }
    }

    initComplete = true;
  }
}
