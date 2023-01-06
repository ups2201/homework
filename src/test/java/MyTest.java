import annotations.Driver;
import extentsions.UIExtentsion;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pages.CoursePage;
import pages.MainPage;
import steps.PageSteps;

@ExtendWith(UIExtentsion.class)
public class MyTest {
    @Driver
    private WebDriver driver;

    @Test
    @DisplayName("Поиск курса с названием 'Специализация QA Automation Engineer'")
    public void clickCourse() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.isLoadPage();
        mainPage.findCourseByTitle("Специализация QA Automation Engineer").click();
        CoursePage coursePage = new CoursePage(driver);
        coursePage.isLoadPage();
    }

}
