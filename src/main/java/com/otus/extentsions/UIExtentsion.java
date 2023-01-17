package com.otus.extentsions;

import com.otus.annotations.Driver;
import com.otus.driver.WebDriverFactory;
import com.otus.listeners.MouseListeners;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class UIExtentsion implements BeforeEachCallback, AfterEachCallback {

  private EventFiringWebDriver driver = null;

  private Set<Field> getAnnotationFields(
      Class<? extends Annotation> annotation, ExtensionContext extensionContext) {
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
        AccessController.doPrivileged((PrivilegedAction<Void>)
            () -> {
              try {
                field.setAccessible(true);
                field.set(extensionContext.getTestInstance().get(), driver);
              } catch (IllegalAccessException e) {
                throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field), e);
              }
              return null;
            }
        );
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
