package com.otus.pages;

import com.google.inject.Inject;
import com.otus.annotations.PageName;
import com.otus.annotations.UrlPrefix;
import com.otus.configs.GuiceScoped;
import org.openqa.selenium.support.PageFactory;

@PageName("Главная страница")
@UrlPrefix("/")
public class MainPage extends AbstractBasePage<MainPage> {

  @Inject
  public MainPage(GuiceScoped guiceScoped) {
    super(guiceScoped);
    PageFactory.initElements(driver, this);
  }
}
