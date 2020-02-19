export class Employee {
    id: number;
    name: string;
    surname: string;
    phoneNumber: string;
    position: string;
    salary: number;
    photography: string;



    public static copyOf(employee: Employee): Employee {
      return Object.assign(new Employee(), employee);
  }

  public clone(): Employee {
      return Employee.copyOf(this);
  }
  }