package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.StandardWaiter;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class CommonActions<T> {

  protected WebDriver driver;
  protected StandardWaiter standardWaiter;
  protected Actions actions;

  public CommonActions(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);

    standardWaiter = new StandardWaiter(driver);
    actions = new Actions(driver);
  }

}
