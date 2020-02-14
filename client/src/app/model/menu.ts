export class Menu {
    id: number;
    name: string;

    public static copyOf(menu: Menu): Menu {
      return Object.assign(new Menu(), menu);
  }

  public clone(): Menu {
      return Menu.copyOf(this);
  }
  }