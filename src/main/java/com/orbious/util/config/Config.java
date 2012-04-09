package com.orbious.util.config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

public class Config {

  protected static final String app_version = "app_version";
  protected static final String log_realm = "log_realm";
  protected static final String log_config = "log_config";

  public static final String config_hdb_key = Config.class.getName();
  public static final String config_fdb_idx = "1";

  private static Preferences preferences;

  static {
    preferences = Preferences.userRoot().node(Config.class.getName());
  }

  private Config() { }

  public static String appVersion() {
    return preferences.get(app_version, "");
  }

  public static String logRealm() {
    return preferences.get(log_realm, "");
  }

  public static String logConfig() {
    return preferences.get(log_config, "");
  }

  public static String get(String xmlstr, IConfig key) {
    ByteArrayOutputStream out;
    ByteArrayInputStream in;
    String value;
    boolean failedImport;

    value = null;

    // export what we allready have
    out = null;
    try {
      out = new ByteArrayOutputStream();
      preferences.exportNode(out);
    } catch ( BackingStoreException bse ) {
      return value;
    } catch ( IOException ioe ) {
      return value;
    }

    failedImport = false;
    in = new ByteArrayInputStream(xmlstr.getBytes());
    try {
      Preferences.importPreferences(in);
    } catch ( InvalidPreferencesFormatException ipfe ) {
      failedImport = true;
    } catch ( IOException ioe ) {
      failedImport = true;
    }

    if ( !failedImport ) {
      value = preferences.get(key.getName(), "");
    }

    try {
      Preferences.importPreferences(new ByteArrayInputStream(out.toByteArray()));
    } catch ( InvalidPreferencesFormatException ignored ) {
      // this should not happen
    } catch ( IOException ignored ) {
      // this should not happen
    }

    return value;
  }

  public static void putString(IConfig key, String value) {
    preferences.put(key.getName(), value);
  }

  public static String getString(IConfig key) {
    return preferences.get(key.getName(), "");
  }

  public static void putInt(IConfig key, int value) {
    preferences.putInt(key.getName(), value);
  }

  public static int getInt(IConfig key) {
    return preferences.getInt(key.getName(), -1);
  }

  public static void putFloat(IConfig key, float value) {
    preferences.putFloat(key.getName(), value);
  }

  public static float getFloat(IConfig key) {
    return preferences.getFloat(key.getName(), Float.NaN);
  }

  public static void putDouble(IConfig key, double value) {
    preferences.putDouble(key.getName(), value);
  }

  public static double getDouble(IConfig key) {
    return preferences.getDouble(key.getName(), Double.NaN);
  }

  public static void putLong(IConfig key, long value) {
    preferences.putLong(key.getName(), value);
  }

  public static long getLong(IConfig key) {
    return preferences.getLong(key.getName(), -1);
  }


  public static void putBoolean(IConfig key, boolean value) {
    preferences.putBoolean(key.getName(), value);
  }

  public static boolean getBool(IConfig key) {
    return preferences.getBoolean(key.getName(), false);
  }

  public static void setLastKnownGood() {
    throw new UnsupportedOperationException("Not yet implemented..");
  }

  public static void setDefaults(Class<?> clazz) throws ConfigException {
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
      } else if ( cfg.isLong() ) {
        preferences.putLong(name, cfg.asLong());
      } else if ( cfg.isFloat() ) {
        preferences.putFloat(name, cfg.asFloat());
      } else if ( cfg.isDouble() ) {
        preferences.putDouble(name, cfg.asDouble());
      } else if ( cfg.isBool() ) {
        preferences.putBoolean(name, cfg.asBool());
      }
    }

    validate();
  }

  public static void setDefaults(String cfgstr) throws ConfigException {
    ByteArrayInputStream is = new ByteArrayInputStream(cfgstr.getBytes());
    try {
      Preferences.importPreferences(is);
    } catch ( InvalidPreferencesFormatException ipfe ) {
      throw new ConfigException("Invalid preference format for:\n" +
          cfgstr + "\n\n", ipfe);
    } catch ( IOException ioe ) {
      throw new ConfigException("IOException parsing config:\n" +
          cfgstr + "\n\n", ioe);
    }

    validate();
  }


  public static void verify(Class<?> clazz) throws ConfigException {
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

  public static String xmlstr() throws ConfigException {
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

  private static void validate() throws ConfigException {
    if ( preferences.get(log_realm, "") == "" ) {
      throw new ConfigException("Defaults missing " + log_realm);
    } else if ( preferences.get(log_config, "") == "" ) {
      throw new ConfigException("Defaults missing " + log_config);
    } else if ( preferences.get(app_version, "") == "" ) {
      throw new ConfigException("Defaults missing version " + app_version);
    }
  }
}
