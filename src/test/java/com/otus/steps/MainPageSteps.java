package com.otus.steps;

import com.google.inject.Inject;
import com.otus.configs.GuiceScoped;
import com.otus.driver.WebDriverFactory;
import com.otus.pages.MainPage;
import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.ru.Пусть;

@ScenarioScoped
public class MainPageSteps {
  @Inject
  GuiceScoped guiceScoped;

  @Пусть("Открываем главную страницу в браузере {string}")
  public void open(String browserType) {
    guiceScoped.browserType = browserType;
    guiceScoped.driver = new WebDriverFactory(guiceScoped).getDriver();
    MainPage mainPage = new MainPage(guiceScoped);
    mainPage.open();
  }
}
