package com.otus.exceptions;

import io.github.bonigarcia.wdm.config.DriverManagerType;

public class DriverTypeNotSupported extends Exception {

  public DriverTypeNotSupported(DriverManagerType driverType) {
    super(String.format("Browser type %s not supported", driverType.getBrowserName()));
  }

  public DriverTypeNotSupported(String driverType) {
    super(String.format("Browser type %s not supported", driverType));
  }
}
