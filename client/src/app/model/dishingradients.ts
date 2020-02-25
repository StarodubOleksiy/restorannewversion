export class DishIngradient {

    dishId: number;
    ingradientId: number;
    ingradientName: string;
    numerosity: number;

    public static copyOf(ingradient: DishIngradient): DishIngradient {
      return Object.assign(new DishIngradient(), ingradient);
  }

  public clone(): DishIngradient {
      return DishIngradient.copyOf(this);
  }
  }
