package data.menu;

public enum MenuItemData implements IMenuItem {
  LEARNING("Обучение"),
  INFO("Информация");

  private String name;

  MenuItemData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
