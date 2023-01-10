package components.menu;

import annotations.Component;
import components.AbstractComponent;
import data.menu.CourseTypeData;
import data.menu.IMenuItem;
import helpers.AllureHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Component(css = ".header3__nav_main")
public class NavigationMenuComponent extends AbstractComponent<NavigationMenuComponent> {

    public NavigationMenuComponent(WebDriver driver) {
        super(driver);
    }

    @Step
    public NavigationMenuComponent clickSubItem(IMenuItem menuItem, CourseTypeData courseTypeData) {
        AllureHelper.setStepName(String.format("Открываем меню '%s' и нажимаем на тему '%s'", menuItem.getName(), courseTypeData.getName()));
        WebElement menuSubItem = getComponentEntity().findElement(By.xpath(String.format(".//a[contains(text(), '%s')]", courseTypeData.getName())));
        this.moveItem(menuItem).moveToElement(menuSubItem).click().build().perform();

        return this;
    }

    private Actions moveItem(IMenuItem menuItem) {
        WebElement item = driver.findElement(By.xpath(String.format("//span[text()='%s']", menuItem.getName())));

        return actions.moveToElement(item);
    }

}
