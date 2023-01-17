package com.otus.actions;

import com.google.inject.Inject;
import com.otus.configs.GuiceScoped;
import com.otus.waiters.StandardWaiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public abstract class CommonActions<T> {

  protected WebDriver driver;
  protected StandardWaiter standardWaiter;
  protected Actions actions;

  @Inject
  public CommonActions(GuiceScoped guiceScoped) {
    this.driver = guiceScoped.driver;
    PageFactory.initElements(driver, this);

    standardWaiter = new StandardWaiter(driver);
    actions = new Actions(driver);
  }
}
