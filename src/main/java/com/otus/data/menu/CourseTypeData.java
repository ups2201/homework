package com.otus.data.menu;

public enum CourseTypeData {
  Programmer("Программирование"),
  QA("Тестирование"),
  ANALYTICS("Аналитика");

  private String name;

  CourseTypeData(String name) {
    this.name = name;
  }

  public static CourseTypeData valueOfName(String name) {
    for (CourseTypeData courseType : values()) {
      if (courseType.name.equals(name)) {
        return courseType;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }
}
