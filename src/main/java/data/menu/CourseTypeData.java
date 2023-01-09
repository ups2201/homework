package data.menu;

public enum CourseTypeData {
  Programmer("Программирование"),
  QA("Тестирование"),
  ANALYTICS("Аналитика");

  private String name;

  CourseTypeData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
