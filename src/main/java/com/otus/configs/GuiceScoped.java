package com.otus.configs;

import com.otus.driver.WebDriverFactory;
import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class GuiceScoped {

  public WebDriver driver = new WebDriverFactory().getDriver();
}
