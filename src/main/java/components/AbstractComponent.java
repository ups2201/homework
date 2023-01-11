package components;

import actions.CommonActions;
import annotations.Component;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class AbstractComponent<T> extends CommonActions<T> {
  {
    this.standardWaiter.waitForCondition(
        ExpectedConditions.visibilityOfElementLocated(getComponentLocator()));
  }

  public AbstractComponent(WebDriver driver) {
    super(driver);
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
