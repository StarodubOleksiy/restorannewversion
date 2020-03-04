export class Order {
    
    id: number;
    tableNumber: string;
    orderDate: string;
    waiterName: string;
    waiterSurname: string;
    state: string;

    public static copyOf(order: Order): Order {
      return Object.assign(new Order(), order);
  }

  public clone(): Order {
      return Order.copyOf(this);
  }
  }