package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import pages.AbstractBasePage;

public class PageSteps {

    @Step("Нажимаем на элемент")
    public static void clickElement(WebElement webElement) {
        webElement.click();
    }

    @Step("Проверяем что страница загрузилась")
    public static void isLoadPage(AbstractBasePage page) {
        page.isLoadPage();
    }
}
