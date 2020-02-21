export class Ingradient {
    id: number;
    name: string;
    numberOnStorage: number;

    public static copyOf(ingradient: Ingradient): Ingradient {
      return Object.assign(new Ingradient(), ingradient);
  }

  public clone(): Ingradient {
      return Ingradient.copyOf(this);
  }
  }