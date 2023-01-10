package actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.StandardWaiter;


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
