package components;

import actions.CommonActions;
import annotations.Component;
import annotations.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractComponent<T> extends CommonActions<T> {
    @Driver
    protected WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        super(driver);
    }

    private By getComponentLocator() {
        Component component = getClass().getAnnotation(Component.class);

        if (component != null) {
            if (component.xpath() != null) {
                return By.xpath(component.xpath());
            } else if (component.css() != null) {
                return By.cssSelector(component.css());
            }
        }

        return null;
    }

    protected WebElement getComponentEntity() {
        return driver.findElement(this.getComponentLocator());
    }
}
