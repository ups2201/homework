package pages;

import annotations.Identifier;
import annotations.PageName;
import annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@PageName("Тема курсов")
@UrlTemplate("/categories/{1}")
@Identifier(xpath = "//h1[contains(text(), '%s')]")
public class CatalogCoursePage extends AbstractBasePage<CatalogCoursePage> {

  public CatalogCoursePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }
}
