package components.courses;

import components.AbstractComponent;
import helpers.StringHelper;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CourseComponent extends AbstractComponent<CourseComponent> {
  public List<WebElement> lessons =
      driver.findElements(By.xpath("//div[@class='lessons__new-item-start']/ancestor::a"));

  public CourseComponent(WebDriver driver) {
    super(driver);
  }

  public String getTitle(WebElement course) {
    return course.findElement(By.cssSelector(".lessons__new-item-title")).getText();
  }

  @Step("Находим курс с названием '{title}'")
  public WebElement findCourseByTitle(String title) {
    Optional<WebElement> element =
        lessons.stream()
            .filter(
                webElement -> {
                  WebElement titleWebElement =
                      webElement.findElement(By.cssSelector(".lessons__new-item-title"));
                  return titleWebElement.getText().equals(title);
                })
            .findFirst();

    Assertions.assertThat(element.isPresent()).withFailMessage("Не найден курс с названием " + title).isTrue();
    return element.get();
  }

  @Step("Получаем курс с датой начала позже всех")
  public WebElement getCourseWithMaxDate() {
    Assertions.assertThat(lessons.size()).withFailMessage("Курсов не найдено").isGreaterThan(1);

    WebElement course =
        lessons.stream()
            .reduce(
                lessons.get(0),
                (webElement1, webElement2) -> {
                  LocalDate localDate1 = getDateFromCourse(webElement1);
                  LocalDate localDate2 = getDateFromCourse(webElement2);

                  if (localDate1.isAfter(localDate2)) {
                    return webElement1;
                  }
                  return webElement2;
                });

    return course;
  }

  public LocalDate getDateFromCourse(WebElement webElement) {
    String date = webElement.findElement(By.cssSelector(".lessons__new-item-start")).getText();
    if (date.endsWith("года")) {
      return StringHelper.getDateFromString(date, "С dd MMMM yyyy года");
    }
    return StringHelper.getDateFromString(date + " " + LocalDate.now().getYear(), "С dd MMMM yyyy");
  }
}
