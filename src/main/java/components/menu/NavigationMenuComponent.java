package components.menu;

import annotations.Component;
import components.AbstractComponent;
import data.menu.CourseTypeData;
import data.menu.IMenuItem;
import helpers.StringHelper;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component(css = ".header3__nav_main")
public class NavigationMenuComponent extends AbstractComponent<NavigationMenuComponent> {

    public NavigationMenuComponent(WebDriver driver) {
        super(driver);
    }

    public NavigationMenuComponent clickSubItem(CourseTypeData courseTypeData) {
        WebElement menuItem = getComponentEntity().findElement(By.xpath(String.format(".//a[contains(text(), '%s')]", courseTypeData.getName())));
        WebElement menuSubItem = getComponentEntity().findElement(By.xpath(String.format(".//a[contains(text(), '%s')]", courseTypeData.getName())));

        return this;
    }

    public NavigationMenuComponent clickMenuItem(IMenuItem menuItem) {
        WebElement item = driver.findElement(By.xpath(String.format("//span[text()='%s']", menuItem.getName())));
        item.click();
        return this;
    }

    private NavigationMenuComponent moveItem(IMenuItem menuItem) {
        WebElement item = driver.findElement(By.xpath(String.format("//span[text()='%s']", menuItem.getName())));

        actions.moveToElement(item).build().perform();
        return this;
    }

}
