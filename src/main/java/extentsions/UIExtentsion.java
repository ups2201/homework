package extentsions;

import annotations.Driver;
import driver.WebDriverFactory;
import listeners.MouseListeners;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;

public class UIExtentsion implements BeforeEachCallback, AfterEachCallback {

    private EventFiringWebDriver driver = null;
    private Set<Field> getAnnotationFields(Class<? extends Annotation> annotation, ExtensionContext extensionContext) {
        Set<Field> fieldSet = new HashSet<>();
        Class<?> testClass = extensionContext.getTestClass().get();
        while (testClass != null) {
            for (Field field : testClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(annotation)) {
                    fieldSet.add(field);
                }
            }
            testClass = testClass.getSuperclass();
        }

        return fieldSet;
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        driver = new WebDriverFactory().getDriver();
        driver.register(new MouseListeners());
        Set<Field> fields = getAnnotationFields(Driver.class, extensionContext);

        for (Field field : fields) {
            if (field.getType().getName().equals(WebDriver.class.getName())) {
                AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                    try {
                        field.setAccessible(true);
                        field.set(extensionContext.getTestInstance().get(), driver);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                });
            }
        }

    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
