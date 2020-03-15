export class CookedDish {

    id: number;
    dishId: number;
    cookerId: number;
    cookerName: string;
    cookerSurname: string;
    dishPrice: number;
    dishName: string;
    

    public static copyOf(cookedDish: CookedDish): CookedDish {
      return Object.assign(new CookedDish(), cookedDish);
  }

  public clone(): CookedDish {
      return CookedDish.copyOf(this);
  }
  }