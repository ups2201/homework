package pages;

import annotations.Identifier;
import annotations.PageName;
import annotations.UrlPrefix;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@PageName("Детальная информация по курсу")
@UrlPrefix("/lessons/qa-auto-java-specialization/")
@Identifier(xpath = "//h1[contains(text(), 'QA Automation')]")
public class CoursePage extends AbstractBasePage<CoursePage> {

    public CoursePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
