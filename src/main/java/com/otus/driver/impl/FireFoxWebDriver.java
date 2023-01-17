package com.otus.driver.impl;

import com.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

public class FireFoxWebDriver implements IDriver {
  @Override
  public WebDriver newDriver() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    firefoxOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.FIREFOX);
      } catch (DriverTypeNotSupported e) {
        throw new RuntimeException(e);
      }
    }

    return new FirefoxDriver(firefoxOptions);
  }
}
