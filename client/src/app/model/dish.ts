export class Dish {
    id: number;
    name: string;
    image: string;
    price:number;
    weight: number;
    menuId: number;
    menuName: string;
    ingradientsId: number[]; 



    public static copyOf(dish: Dish): Dish {
      return Object.assign(new Dish(), dish);
  }

  public clone(): Dish {
      return Dish.copyOf(this);
  }
  }