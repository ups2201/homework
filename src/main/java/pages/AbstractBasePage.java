package pages;

import annotations.Identifier;
import annotations.PageName;
import annotations.UrlPrefix;
import annotations.UrlTemplate;
import helpers.AllureHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractBasePage<T> {
    protected WebDriver driver;
    private String baseUrl = System.getProperty("webdriver.base.url");

    public AbstractBasePage(WebDriver driver) {
        this.driver = driver;
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

        for (int i = 1; i< urlParameters.length; i++) {
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

    private void checkIdentifierByPage() {
        Identifier identifier = getClass().getAnnotation(Identifier.class);
        try {
            if (identifier == null) {
                return;
            }
            if (identifier.xpath() != null) {
                driver.findElement(By.xpath(identifier.xpath()));
            } else if (identifier.css() != null) {
                driver.findElement(By.cssSelector(identifier.css()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.fail("Не найден ключевой элемент страницы c identifier = " + identifier);
        }
    }

    private String getPageName() {
        PageName pageName = getClass().getAnnotation(PageName.class);
        if (pageName != null) {
            return pageName.value();
        }

        return this.getClass().getSimpleName();
    }

    @Step
    public T isLoadPage(String... urlParameters) {
        AllureHelper.setStepName(String.format("Проверяем что страница '%s' загрузилась", getPageName()));
        checkIdentifierByPage();
        String url = getUrl(urlParameters);
        String currentUrl = driver.getCurrentUrl();

        Assertions.assertThat(url).withFailMessage(String.format("Url страницы %s не совпдает с текущим %s", url, currentUrl)).isEqualTo(currentUrl);

        return (T) this;
    }
}
