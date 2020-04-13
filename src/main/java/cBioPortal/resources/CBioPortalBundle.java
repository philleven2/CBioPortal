package cBioPortal.resources;

import java.io.Serializable;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 * @author LevenP
 *
 * The CBioPortalBundle provides a convenient repository for environment settings.
 *
 */
public final class CBioPortalBundle implements Serializable {

  private static final long serialVersionUID = -546613779465057205L;

  final static Logger log = Logger.getLogger(CBioPortalBundle.class.getName());

  // Bundle name
  private static final String BUNDLE_NAME = "CBioPortal";
  private static final String BUNDLE_NAME2 = "CBioPortalSQL";
  private static final String BUNDLE_NAME4 = "log4j";

  private static final PropertyResourceBundle m_propertyBundle;
  private static final PropertyResourceBundle m_propertyBundle2;
  private static final PropertyResourceBundle m_propertyBundle4;

  // Resource bundle instances created by the getBundle factory methods are cached by default,
  // and the factory methods return the same resource bundle instance multiple times if it has been
  // cached.
  static {

    m_propertyBundle = (PropertyResourceBundle) ResourceBundle.getBundle(BUNDLE_NAME);
    m_propertyBundle2 = (PropertyResourceBundle) ResourceBundle.getBundle(BUNDLE_NAME2);
    m_propertyBundle4 = (PropertyResourceBundle) ResourceBundle.getBundle(BUNDLE_NAME4);

  }

  /**
   * Returns value for a particular key from Howitzer.properties.
   *
   * @param key
   * @return the String value for a particular key
   * @throws IllegalArgumentException if the key or expected value is null.
   *
   */
  public static String getValueForKey(String key) {

    if (key == null) {

      log.error("Error: key cannot be null.");
      throw new IllegalArgumentException("Error: key cannot be null.");

    }

    String value = m_propertyBundle.getString(key);

    if (value == null) {

      log.error("Error: value could not be found.");
      throw new IllegalArgumentException("Error: value could not be found.");

    }

    return value;

  }

  /**
   * Returns value for a particular key from HowitzerSQL.properties.
   *
   * @param key
   * @return the String value for a particular key
   * @throws IllegalArgumentException if the key or expected value is null.
   *
   */
  public static String getValueForKey2(String key) {

    if (key == null) {

      log.error("Error: key cannot be null.");
      throw new IllegalArgumentException("Error: key cannot be null.");

    }

    String value = m_propertyBundle2.getString(key);

    if (value == null) {

      log.error("Error: value could not be found.");
      throw new IllegalArgumentException("Error: value could not be found.");

    }

    return value;

  }

  /**
   * Returns value for a particular key from log4j.properties.
   *
   * @param key
   * @return the String value for a particular key
   * @throws IllegalArgumentException if the key or expected value is null.
   *
   */
  public static String getValueForKey4(String key) {

    if (key == null) {

      log.error("Error: key cannot be null.");
      throw new IllegalArgumentException("Error: key cannot be null.");

    }

    String value = m_propertyBundle4.getString(key);

    if (value == null) {

      log.error("Error: value could not be found.");
      throw new IllegalArgumentException("Error: value could not be found.");

    }

    return value;

  }

}
