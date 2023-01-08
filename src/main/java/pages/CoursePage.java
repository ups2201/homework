package pages;

import annotations.Identifier;
import annotations.PageName;
import annotations.UrlPrefix;
import annotations.UrlTemplate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@PageName("Детальная информация по курсу")
@UrlTemplate("/{1}")
@UrlPrefix("/lessons")
@Identifier(xpath = "//h1[contains(text(), '%s')]")
public class CoursePage extends AbstractBasePage<CoursePage> {

    public CoursePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
