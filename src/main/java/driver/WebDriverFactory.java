package driver;

import driver.impl.ChromeWebDriver;
import driver.impl.FireFoxWebDriver;
import driver.impl.OperaWebDriver;
import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Locale;

public class WebDriverFactory implements IWebDriverFactory {

  private DriverManagerType getBrowserType() {
    String browserTypeProperty = System.getProperty("browser").toUpperCase(Locale.ROOT);
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
