package com.otus.driver.impl;

import com.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;

public class OperaWebDriver implements IDriver {
  @Override
  public WebDriver newDriver() {
    OperaOptions operaOptions = new OperaOptions();
    operaOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    operaOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));

    if (getRemoteUrl() == null) {
      try {
        downloadLocalWebDriver(DriverManagerType.OPERA);
      } catch (DriverTypeNotSupported e) {
        throw new RuntimeException(e);
      }
    }

    return new OperaDriver(operaOptions);
  }
}
