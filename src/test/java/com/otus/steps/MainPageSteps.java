package com.otus.steps;

import com.google.inject.Inject;
import com.otus.components.courses.CourseComponent;
import com.otus.components.menu.NavigationMenuComponent;
import com.otus.configs.GuiceScoped;
import com.otus.data.menu.CourseTypeData;
import com.otus.data.menu.MenuItemData;
import com.otus.driver.WebDriverFactory;
import com.otus.pages.CatalogCoursePage;
import com.otus.pages.MainPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.WebElement;

public class MainPageSteps {
  @Inject
  GuiceScoped guiceScoped;

  @Пусть("^Открываем главную страницу в браузере (chrome|opera|firefox)$")
  public void open(String browserType) {
    guiceScoped.browserType = browserType;
    guiceScoped.driver = new WebDriverFactory(guiceScoped).getDriver();
    MainPage mainPage = new MainPage(guiceScoped);
    mainPage.open();
  }

  @И("^Поиск курса с названием '(.*)'$")
  public void findCourse(String courseName) {
    CourseComponent courseComponent = new CourseComponent(guiceScoped);
    WebElement course = courseComponent.findCourseByTitle(courseName);
    course.click();
  }

  @И("^Получаем курсы больше указанной даты '(.*)' и выводим информацию$")
  public void findCourse2(String date) {
    CourseComponent courseComponent = new CourseComponent(guiceScoped);
    courseComponent.getCourseGreaterDate(date);
  }

  @И("^Получаем курсы с наименьшим и наибольшим периодом в теме 'Тестирование'$")
  public void findShortAndLongTest() {
    MainPage mainPage = new MainPage(guiceScoped);
    mainPage.open();
    NavigationMenuComponent navigationMenuComponent = new NavigationMenuComponent(guiceScoped);
    navigationMenuComponent.clickSubItem(MenuItemData.LEARNING, CourseTypeData.QA);

    CatalogCoursePage catalogCoursePage = new CatalogCoursePage(guiceScoped);
    catalogCoursePage.getShortAndLongCourse();
  }
}
