package com.otus.components;

import com.otus.actions.CommonActions;
import com.otus.annotations.Component;
import com.otus.configs.GuiceScoped;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbstractComponent<T> extends CommonActions<T> {
  {
    this.standardWaiter.waitForCondition(
        ExpectedConditions.visibilityOfElementLocated(getComponentLocator()));
  }

  public AbstractComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  private By getComponentLocator() {
    Component component = getClass().getAnnotation(Component.class);

    if (component != null) {
      if (!component.xpath().isEmpty()) {
        return By.xpath(component.xpath());
      } else if (!component.css().isEmpty()) {
        return By.cssSelector(component.css());
      }
    }

    return null;
  }

  protected WebElement getComponentEntity() {
    return driver.findElement(this.getComponentLocator());
  }
}
