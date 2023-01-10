import annotations.Driver;
import components.courses.CourseComponent;
import components.menu.NavigationMenuComponent;
import data.menu.CourseTypeData;
import data.menu.MenuItemData;
import extentsions.UIExtentsion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.CatalogCoursePage;
import pages.CoursePage;
import pages.MainPage;

@ExtendWith(UIExtentsion.class)
public class MyTest {
    @Driver
    private WebDriver driver;

    @Test
    @DisplayName("Поиск курса с названием 'Administrator Linux. Professional'")
    public void findCourseByNameTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.pageUrlEqualsCurrentUrl();
        CourseComponent courseComponent = new CourseComponent(driver);
        WebElement course = courseComponent.findCourseByTitle("Administrator Linux. Professional");
        course.click();
        CoursePage coursePage = new CoursePage(driver);
        coursePage.pageIdentifierIsExist("Administrator Linux. Professional");
    }

    @Test
    @DisplayName("Проверяем, что курс с датой начала позже всех это 'Системный аналитик. Advanced'")
    public void getMaxDateFromCourseTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.pageUrlEqualsCurrentUrl();
        CourseComponent courseComponent = new CourseComponent(driver);
        WebElement course = courseComponent.getCourseWithMaxDate();
        course.click();
        CoursePage coursePage = new CoursePage(driver);
        coursePage.pageIdentifierIsExist("Системный аналитик. Advanced");
    }

    @Test
    @DisplayName("Открываем блок курсов 'Программирование' через Actions")
    public void openCourseInMenuTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent(driver);
        navigationMenuComponent.clickSubItem(MenuItemData.LEARNING, CourseTypeData.Programmer);

        CatalogCoursePage catalogCoursePage = new CatalogCoursePage(driver);
        catalogCoursePage.pageIdentifierIsExist("Программирование");
    }
}
