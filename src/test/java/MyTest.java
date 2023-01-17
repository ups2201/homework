import com.google.inject.Inject;
import com.otus.annotations.Driver;
import com.otus.components.courses.CourseComponent;
import com.otus.components.menu.NavigationMenuComponent;
import com.otus.data.menu.CourseTypeData;
import com.otus.data.menu.MenuItemData;
import com.otus.extentsions.UIExtentsion;
import com.otus.pages.CatalogCoursePage;
import com.otus.pages.MainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ExtendWith(UIExtentsion.class)
public class MyTest {
  @Driver
  private WebDriver driver;

  @Inject
  private MainPage mainPage;
  @Inject
  private CourseComponent courseComponent;
  @Inject
  private NavigationMenuComponent navigationMenuComponent;
  @Inject
  private CatalogCoursePage catalogCoursePage;

  @Test
  @DisplayName("Поиск курса с названием 'Administrator Linux. Professional'")
  public void findCourseByNameTest() {
    mainPage.open();
    mainPage.pageUrlEqualsCurrentUrl();
    WebElement course = courseComponent.findCourseByTitle("Administrator Linux. Professional");
    course.click();
  }

  @Test
  @DisplayName("Переходим в курс с датой начала позже всех")
  public void getMaxDateFromCourseTest() {
    mainPage.open();
    mainPage.pageUrlEqualsCurrentUrl();
    WebElement course = courseComponent.getCourseWithMaxDate();
    course.click();
  }

  @Test
  @DisplayName("Открываем блок курсов 'Программирование' через Actions")
  public void openCourseInMenuTest() {
    mainPage.open();
    navigationMenuComponent.clickSubItem(MenuItemData.LEARNING, CourseTypeData.Programmer);
    catalogCoursePage.pageIdentifierIsExist("Программирование");
  }
}
