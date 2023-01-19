package com.otus.pages;

import com.otus.annotations.Identifier;
import com.otus.annotations.PageName;
import com.otus.annotations.UrlTemplate;
import com.otus.configs.GuiceScoped;
import io.qameta.allure.Step;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

@PageName("Тема курсов")
@UrlTemplate("/catalog/courses?categories=testing")
@Identifier(xpath = "//h1[contains(text(), '%s')]")
public class CatalogCoursePage extends AbstractBasePage<CatalogCoursePage> {

  public List<WebElement> coursesList =
      driver.findElements(By.xpath("//h1[contains(., 'Каталог')]/../..//a"));

  public CatalogCoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
    PageFactory.initElements(driver, this);
  }

  @Step("Получаем самый которкий и самый продолжительный курс")
  public void getShortAndLongCourse() {
    Assertions.assertThat(coursesList.size()).withFailMessage("Курсов не найдено").isGreaterThan(1);

    WebElement maxCourse =
        coursesList.stream()
            .reduce(
                coursesList.get(0),
                (webElement1, webElement2) -> {
                  int period1 = getDateFromCourse(webElement1);
                  int period2 = getDateFromCourse(webElement2);

                  if (period1 > period2) {
                    return webElement1;
                  }
                  return webElement2;
                });

    WebElement minCourse =
        coursesList.stream()
            .reduce(
                coursesList.get(0),
                (webElement1, webElement2) -> {
                  int period1 = getDateFromCourse(webElement1);
                  int period2 = getDateFromCourse(webElement2);

                  if (period1 < period2) {
                    return webElement1;
                  }
                  return webElement2;
                });

    String maxName = maxCourse.findElement(By.xpath("./h6")).getText();
    String maxPeriod = maxCourse.findElement(By.xpath("./h6/following-sibling::div")).getText();

    String minName = minCourse.findElement(By.xpath("./h6")).getText();
    String minPeriod = minCourse.findElement(By.xpath("./h6/following-sibling::div")).getText();

    System.out.println("Самый длинный курс - " + maxName);
    System.out.println("Продолжительность длинного курса - " + maxPeriod);
    System.out.println("Самый короткий курс - " + minName);
    System.out.println("Продолжительность короткого курса - " + minPeriod);
  }

  public int getDateFromCourse(WebElement webElement) {
    String date = webElement.findElement(By.xpath("./h6/following-sibling::div")).getText();
    Pattern pattern = Pattern.compile("(.*) · (.*) (.*)");
    Matcher matcher = pattern.matcher(date);
    if (matcher.find()) {
      return Integer.parseInt(matcher.group(2));
    }

    return 0;
  }
}
