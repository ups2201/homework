package com.otus.driver;

import com.google.inject.Inject;
import com.otus.configs.GuiceScoped;
import com.otus.driver.impl.ChromeWebDriver;
import com.otus.driver.impl.FireFoxWebDriver;
import com.otus.driver.impl.OperaWebDriver;
import com.otus.exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import java.util.Locale;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class WebDriverFactory implements IWebDriverFactory {

  public GuiceScoped guiceScoped;


  @Inject
  public WebDriverFactory(GuiceScoped guiceScoped) {
    this.guiceScoped = guiceScoped;
  }

  public WebDriverFactory() {

  }

  private DriverManagerType getBrowserType() {
    String browserTypeProperty = guiceScoped.browserType.toUpperCase(Locale.ROOT);
    return DriverManagerType.valueOf(browserTypeProperty);
  }

  @Override
  public EventFiringWebDriver getDriver() {
    DriverManagerType browserType = getBrowserType();
    switch (browserType) {
      case CHROME:
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
      case OPERA:
        return new EventFiringWebDriver(new OperaWebDriver().newDriver());
      case FIREFOX:
        return new EventFiringWebDriver(new FireFoxWebDriver().newDriver());
      default:
        try {
          throw new DriverTypeNotSupported(browserType);
        } catch (DriverTypeNotSupported e) {
          e.printStackTrace();
          return null;
        }
    }
  }
}
