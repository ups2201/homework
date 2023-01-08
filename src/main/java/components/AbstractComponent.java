package components;

import annotations.Driver;
import org.openqa.selenium.WebDriver;

public abstract class AbstractComponent<T> {
    @Driver
    protected WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }
}
