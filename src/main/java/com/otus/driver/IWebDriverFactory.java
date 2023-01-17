package com.otus.driver;

import com.otus.exceptions.DriverTypeNotSupported;
import org.openqa.selenium.WebDriver;

public interface IWebDriverFactory {
  WebDriver getDriver() throws DriverTypeNotSupported;
}
