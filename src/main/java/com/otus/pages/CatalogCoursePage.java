package com.otus.pages;

import com.otus.annotations.Identifier;
import com.otus.annotations.PageName;
import com.otus.annotations.UrlTemplate;
import com.otus.configs.GuiceScoped;
import org.openqa.selenium.support.PageFactory;

@PageName("Тема курсов")
@UrlTemplate("/categories/{1}")
@Identifier(xpath = "//h1[contains(text(), '%s')]")
public class CatalogCoursePage extends AbstractBasePage<CatalogCoursePage> {

  public CatalogCoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
    PageFactory.initElements(driver, this);
  }
}
