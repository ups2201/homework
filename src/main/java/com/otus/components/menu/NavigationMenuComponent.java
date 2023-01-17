package com.otus.components.menu;

import com.otus.annotations.Component;
import com.otus.components.AbstractComponent;
import com.otus.configs.GuiceScoped;
import com.otus.data.menu.CourseTypeData;
import com.otus.data.menu.IMenuItem;
import com.otus.helpers.AllureHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@Component(css = ".header3__nav_main")
public class NavigationMenuComponent extends AbstractComponent<NavigationMenuComponent> {

  public NavigationMenuComponent(GuiceScoped guiceScoped) {
    super(guiceScoped);
  }

  @Step
  public NavigationMenuComponent clickSubItem(IMenuItem menuItem, CourseTypeData courseTypeData) {
    AllureHelper.setStepName(
        String.format(
            "Открываем меню '%s' и нажимаем на тему '%s'",
            menuItem.getName(), courseTypeData.getName()));
    WebElement menuSubItem =
        getComponentEntity()
            .findElement(
                By.xpath(String.format(".//a[contains(text(), '%s')]", courseTypeData.getName())));
    this.moveItem(menuItem).moveToElement(menuSubItem).click().build().perform();

    return this;
  }

  private Actions moveItem(IMenuItem menuItem) {
    WebElement item =
        driver.findElement(By.xpath(String.format("//span[text()='%s']", menuItem.getName())));

    return actions.moveToElement(item);
  }
}
