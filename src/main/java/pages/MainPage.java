package pages;

import annotations.PageName;
import annotations.UrlPrefix;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@PageName("Главная страница")
@UrlPrefix("/")
public class MainPage extends AbstractBasePage<MainPage> {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='lessons__new-item-container']")
    public WebElement courseLabel;

    @FindBy(xpath = "//div[contains(@class,'lessons__new-item-title')]")
    public WebElement courseTitleLabel;

    @FindBy(xpath = "//div[@class='lessons__new-item-container']")
    public List<WebElement> courseLabelList;

    public CoursePage clickCourse() {
        CoursePage coursePage = new CoursePage(driver);
        return coursePage;
    }

    @Step("Находим курс с названием '{title}'")
    public WebElement findCourseByTitle(String title) {
        String xPath = "//div[contains(@class,'lessons__new-item-title') and contains(text(), '%s')]";
        try {
            return driver.findElement(By.xpath(String.format(xPath, title)));
        } catch (Exception e) {
            Assertions.fail("Не найден курс с названием " + title);
            return null;
        }
    }

    public void openCourseByDate() {

    }
}
