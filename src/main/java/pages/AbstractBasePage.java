package pages;

import actions.CommonActions;
import annotations.Identifier;
import annotations.PageName;
import annotations.UrlPrefix;
import annotations.UrlTemplate;
import helpers.AllureHelper;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class AbstractBasePage<T> extends CommonActions<T> {
  private String baseUrl = System.getProperty("webdriver.base.url");

  public AbstractBasePage(WebDriver driver) {
    super(driver);
  }

  private String getUrlPrefix() {
    UrlPrefix urlPrefix = getClass().getAnnotation(UrlPrefix.class);
    if (urlPrefix != null) {
      String prefix = urlPrefix.value();
      if (!prefix.startsWith("/")) {
        return "/" + prefix;
      }
      return prefix;
    }
    return "";
  }

  private String getUrlTemplate() {
    UrlTemplate urlTemplate = getClass().getAnnotation(UrlTemplate.class);
    if (urlTemplate != null) {
      return urlTemplate.value();
    }

    return "";
  }

  protected String getUrl(String... urlParameters) {
    String urlPrefix = getUrlPrefix();
    String urlTemplate = getUrlTemplate();

    for (int i = 1; i < urlParameters.length; i++) {
      urlTemplate = urlTemplate.replace(String.format("{%d}", i), urlParameters[i]);
    }

    if (urlPrefix.isEmpty()) {
      return baseUrl + urlTemplate;
    }

    return baseUrl + urlPrefix + urlTemplate;
  }

  @Step
  public T open(String... urlParameters) {
    AllureHelper.setStepName(String.format("Открываем страницу '%s'", getPageName()));

    String url = getUrl(urlParameters);
    driver.get(url);

    return (T) this;
  }

  @Step("Проверяем что идентификатор страницы существует")
  public T pageIdentifierIsExist(String... args) {
    Identifier identifier = getClass().getAnnotation(Identifier.class);
    try {
      if (identifier == null) {
        return (T) this;
      }
      if (identifier.xpath() != null) {
        String xpath = String.format(identifier.xpath(), (Object[]) args);
        driver.findElement(By.xpath(xpath));
      } else if (identifier.css() != null) {
        String css = String.format(identifier.css(), (Object[]) args);
        driver.findElement(By.cssSelector(css));
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Не найден ключевой элемент страницы c identifier = " + identifier);
    }
    return (T) this;
  }

  private String getPageName() {
    PageName pageName = getClass().getAnnotation(PageName.class);
    if (pageName != null) {
      return pageName.value();
    }

    return this.getClass().getSimpleName();
  }

  @Step
  public T pageUrlEqualsCurrentUrl(String... urlParameters) {
    AllureHelper.setStepName(
        String.format("Проверяем что страница '%s' загрузилась", getPageName()));
    String url = getUrl(urlParameters);
    String currentUrl = driver.getCurrentUrl();

    Assertions.assertThat(url)
        .withFailMessage(String.format("Url страницы %s не совпдает с текущим %s", url, currentUrl))
        .isEqualTo(currentUrl);

    return (T) this;
  }

  @Step
  public T pageTitleShouldBeSameAs(String title) {
    AllureHelper.setStepName(
        String.format(
            "Проверяем что страница '%s' открылась с названием вкладки '%s'",
            getPageName(), title));
    Assertions.assertThat(driver.getTitle())
        .withFailMessage("Название текущей вкладки не совпадает")
        .isEqualTo(title);
    return (T) this;
  }
}
