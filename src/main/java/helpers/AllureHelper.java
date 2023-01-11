package helpers;

import io.qameta.allure.Allure;

public class AllureHelper {
  public static void setStepName(String stepName) {
    Allure.getLifecycle().updateStep((stepResult -> stepResult.setName(stepName)));
  }
}
