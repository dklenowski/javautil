package com.orbious.util;

/**
 * A singleton instance for applications using shutdown hooks.
 *
 * @author dave
 */
public class Command {

  private static Command instance;

  private boolean shutdown = false;
  private boolean can_exit = false;

  private Command() { }

  public static Command instance() {
    if ( instance == null )
      instance = new Command();

    return instance;
  }

  public boolean shutdown() {
    return shutdown;
  }

  public void shutdown(boolean shutdown) {
    this.shutdown = shutdown;
  }

  public boolean canExit() {
    return can_exit;
  }

  public void canExit(boolean canExit) {
    can_exit = canExit;
  }

}

