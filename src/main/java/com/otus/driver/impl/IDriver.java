package com.otus.driver.impl;

import com.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;

public interface IDriver {
  String REMOTE_URL = System.getProperty("webdriver.remote.url");
  boolean HEADLESS = Boolean.parseBoolean(System.getProperty("webdriver.headless"));

  WebDriver newDriver();

  default URL getRemoteUrl() {
    try {
      return new URL(REMOTE_URL);
    } catch (MalformedURLException e) {
      return null;
    }
  }

  default void downloadLocalWebDriver(DriverManagerType driverManagerType)
      throws DriverTypeNotSupported {
    Config config = WebDriverManager.globalConfig();
    config.setAvoidBrowserDetection(true);

    String browserVersion = System.getProperty("browser.version", "");

    if (!browserVersion.isEmpty()) {
      switch (driverManagerType) {
        case CHROME:
          config.setChromeDriverVersion(browserVersion);
          break;
        case OPERA:
          config.setOperaDriverVersion(browserVersion);
          break;
        default:
          throw new DriverTypeNotSupported(driverManagerType);
      }
    }

    WebDriverManager.getInstance(driverManagerType).setup();
  }
}
