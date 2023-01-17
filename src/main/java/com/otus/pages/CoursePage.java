package com.otus.pages;

import com.otus.annotations.Identifier;
import com.otus.annotations.PageName;
import com.otus.annotations.UrlPrefix;
import com.otus.annotations.UrlTemplate;
import com.otus.configs.GuiceScoped;
import org.openqa.selenium.support.PageFactory;

@PageName("Детальная информация по курсу")
@UrlTemplate("/{1}")
@UrlPrefix("/lessons")
@Identifier(xpath = "//h1[contains(text(), '%s')]")
public class CoursePage extends AbstractBasePage<CoursePage> {

  public CoursePage(GuiceScoped guiceScoped) {
    super(guiceScoped);
    PageFactory.initElements(driver, this);
  }
}
