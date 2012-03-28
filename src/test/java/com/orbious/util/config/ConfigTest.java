package com.orbious.util.config;

import com.orbious.util.config.Config;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class ConfigTest {

  public final String configstr =
  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
  "<!DOCTYPE preferences SYSTEM \"http://java.sun.com/dtd/preferences.dtd\">\n" +
  "<preferences EXTERNAL_XML_VERSION=\"1.0\">\n" +
  "  <root type=\"user\">\n" +
  "    <map/>\n" +
  "    <node name=\"com.orbious.util.config.Config\">\n" +
  "      <map>\n" +
  "        <entry key=\"a_bool\" value=\"true\"/>\n" +
  "        <entry key=\"app_version\" value=\"3.0\"/>\n" +
  "        <entry key=\"log_realm\" value=\"loggingrealm\"/>\n" +
  "        <entry key=\"logger_realm\" value=\"log-realm\"/>\n" +
  "        <entry key=\"log_config\" value=\"log.config\"/>\n" +
  "        <entry key=\"load_factor\" value=\"1.0\"/>\n" +
  "        <entry key=\"version\" value=\"3.0\"/>\n" +
  "        <entry key=\"cache_size\" value=\"60000\"/>\n" + // note the change (from Constants.class)
  "        <entry key=\"error_rate\" value=\"1.2f\"/>\n" +  // second change
  "      </map>\n" +
  "    </node>\n" +
  "  </root>\n" +
  "</preferences>\n";

	@Test
	public void type() throws Exception {
		assertThat(Config.class, notNullValue());
	}

	@Test
	public void setDefaults_A$Class() throws Exception {
	  Config.setDefaults(Constants.class);

	  assertThat(Config.getString(Constants.app_version), is(equalTo("3.0")));
    assertThat(Config.getString(Constants.log_realm), is(equalTo("loggingrealm")));
    assertThat(Config.getString(Constants.log_config), is(equalTo("log.config")));
    assertEquals(50000, Config.getInt(Constants.cache_size));
    assertEquals(1.1f, Config.getFloat(Constants.error_rate), 0.0);
    assertEquals(1.0, Config.getDouble(Constants.load_factor), 0.0);
    assertEquals(true, Config.getBool(Constants.a_bool));
	}

	@Test
	public void loadSave_A$() throws Exception {
	  Config.setDefaults(Constants.class);
	  Config.setDefaults(configstr);

    assertThat(Config.getString(Constants.app_version), is(equalTo("3.0")));
    assertThat(Config.getString(Constants.log_realm), is(equalTo("loggingrealm")));
    assertThat(Config.getString(Constants.log_config), is(equalTo("log.config")));
    assertEquals(60000, Config.getInt(Constants.cache_size));
    assertEquals(1.2f, Config.getFloat(Constants.error_rate), 0.0);
    assertEquals(1.0, Config.getDouble(Constants.load_factor), 0.0);
    assertEquals(true, Config.getBool(Constants.a_bool));
	}

	@Test
	public void get_A$() throws Exception {
	  Config.setDefaults(Constants.class);
	  Config.putString(Constants.app_version, "4.0");

	  String xmlstr = Config.xmlstr();
	  String actual = Config.get(xmlstr, Constants.log_realm);
	  assertThat(actual, is(equalTo("loggingrealm")));
	  assertThat(Config.getString(Constants.app_version), is(equalTo("4.0")));
	}


	public enum Constants implements IConfig {
	  app_version("3.0"),
	  log_realm("loggingrealm"),
	  log_config("log.config"),
	  cache_size(50000),
	  load_factor(1.0),
	  error_rate(1.1f),
	  a_bool(true);

	  private String svalue = null;
	  private int ivalue = -1;
	  private float fvalue = Float.NaN;
	  private double dvalue = Double.NaN;
	  private Boolean bvalue = null;

	  private Constants(String value) {
	    svalue = value;
	  }

	  public boolean isString() {
	    return (svalue != null) ? true : false;
	  }

	  public String asString() {
	    return svalue;
	  }

	  private Constants(int value) {
	    ivalue = value;
	  }

	  public boolean isInt() {
	    return (ivalue != -1) ? true : false;
	  }

	  public int asInt() {
	    return ivalue;
	  }

    private Constants(double value) {
      dvalue = value;
    }

    public boolean isFloat() {
      return (!Float.isNaN(fvalue)) ? true : false;
    }

    public float asFloat() {
      return fvalue;
    }

    public boolean isDouble() {
      return (!Double.isNaN(dvalue)) ? true : false;
    }

    public double asDouble() {
      return dvalue;
    }

    private Constants(boolean value) {
      bvalue = value;
    }

    public boolean isBool() {
      return (bvalue != null) ? true : false;
    }

    public boolean asBool() {
      return bvalue;
    }

    public String getName() {
      return this.name();
    }
	}
}
