package pages;

import annotations.PageName;
import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@PageName("Главная страница")
@UrlPrefix("/")
public class MainPage extends AbstractBasePage<MainPage> {
  public MainPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }
}
