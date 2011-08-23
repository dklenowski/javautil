package com.orbious.util.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

public class Config {

  public static final String app_version = "app_version";
  public static final String log_realm = "log_realm";
  public static final String log_config = "log_config";

  private static Config instance;
  private Preferences preferences;

  private Config() {
    preferences = Preferences.userRoot().node(Config.class.getName());
  }

  public static Config instance() {
    if ( instance == null ) {
      instance = new Config();
    }
    return instance;
  }

  public void put(IConfig key, String value) {
    preferences.put(key.getName(), value);
  }

  public String getString(IConfig key) {
    return preferences.get(key.getName(), "");
  }

  public void put(IConfig key, int value) {
    preferences.putInt(key.getName(), value);
  }

  public int getInt(IConfig key) {
    return preferences.getInt(key.getName(), -1);
  }

  public void put(IConfig key, double value) {
    preferences.putDouble(key.getName(), value);
  }

  public double getDouble(IConfig key) {
    return preferences.getDouble(key.getName(), Double.NaN);
  }

  public void setDefaults(Class<?> clazz) throws ConfigException {
    IConfig[] cfgs;
    String name;
    IConfig cfg;

    verify(clazz);

    try {
      preferences.clear();
    } catch ( BackingStoreException ignored ) { }

    cfgs = (IConfig[])clazz.getEnumConstants();
    for ( int i = 0; i < cfgs.length; i++ ) {
      cfg = cfgs[i];
      name = cfg.getName();
      if ( cfg.isString() ) {
        preferences.put(name, cfg.asString());
      } else if ( cfg.isInt() ) {
        preferences.putInt(name, cfg.asInt());
      } else if ( cfg.isDouble() ) {
        preferences.putDouble(name, cfg.asDouble());
      }
    }

    validate();
  }

  public void verify(Class<?> clazz) throws ConfigException {
    if ( !clazz.isEnum() ) {
      throw new ConfigException("Cannot set config defaults " +
        clazz.toString() + " is not an enum?");
    }

    boolean fnd = false;
    Class<?> clazzes[] = clazz.getInterfaces();
    for ( int i = 0; i < clazzes.length; i++ ) {
      if ( clazzes[i].getSimpleName().equals(IConfig.class.getSimpleName()) ) {
        fnd = true;
      }
    }

    if ( !fnd ) {
      throw new ConfigException("Cannot set config defaults " +
          clazz.toString() + " does not implement " + IConfig.class.getName());
    }
  }

  public String getConfig() throws ConfigException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
      preferences.exportSubtree(os);
    } catch ( BackingStoreException bse ) {
      throw new ConfigException("BackingStoreException retreiving config for " +
          preferences.absolutePath(), bse);
    } catch ( IOException ioe ) {
      throw new ConfigException("IOException retreiving config for " +
          preferences.absolutePath(), ioe);
    }
    return os.toString();
  }

  public void loadConfig(String str) throws ConfigException {
    ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes());
    try {
      Preferences.importPreferences(is);
    } catch ( InvalidPreferencesFormatException ipfe ) {
      throw new ConfigException("Invalid preference format for:\n" +
          str + "\n\n", ipfe);
    } catch ( IOException ioe ) {
      throw new ConfigException("IOException parsing config:\n" +
          str + "\n\n", ioe);
    }
  }

  public void validate() throws ConfigException {
    if ( preferences.get(log_realm, "") == "" ) {
      throw new ConfigException("Defaults missing " + log_realm);
    } else if ( preferences.get(log_config, "") == "" ) {
      throw new ConfigException("Defaults missing " + log_config);
    } else if ( preferences.get(app_version, "") == "" ) {
      throw new ConfigException("Defaults missing version " + app_version);
    }
  }
}
