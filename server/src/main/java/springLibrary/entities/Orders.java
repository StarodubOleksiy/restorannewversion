package springLibrary.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders  extends AbstractIdentifiableEntity{

/*public Orders()
{
    this.dishes = new ArrayList<Cooked_Dish>();
    for(int i = 0; i < this.dishes.size(); ++i)
    {
        Cooked_Dish dish = dishes.get(i);
        for(int j = i; j < this.dishes.size(); ++j  )
            if(dishes.get(i).equals(dish))
                dishes.remove(i);
    }
    this.state = "open";
    this.orderDate = new Date().toString();
}
*/

   /* @ManyToOne
    @JoinColumn(name = "employee_id")
    private Waiter waiter;*/

   /* @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "dish_to_order",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )*/


   // private List<Dish> dishes;
   /*@OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Cooked_Dish> dishes;*/

    @NotNull
    @Column(name = "table_number", nullable = false)
    private int tableNumber;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @NotNull
    @Column(name = "state" , nullable = false)
    private String state;

  /*  public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public List<Cooked_Dish> getDishes() {
        for(int i = 0; i < dishes.size(); ++i)
        {
            Cooked_Dish dish = dishes.get(i);
            for(int j = i+1; j < dishes.size(); ++j  ) {
                if (j == dishes.size())
                    break;
                if (dishes.get(j).equals(dish)) {
                    dishes.remove(j);
                   }
            }
        }
        return dishes;
    }

    public void setDishes(List<Cooked_Dish> dishes) {
        System.out.println("setDishes(List<Dish> dishes) = " + dishes.size());
        this.dishes = dishes;
    }*/

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (tableNumber != orders.tableNumber) return false;
      //  if (dishes != null ? !dishes.equals(orders.dishes) : orders.dishes != null) return false;
        if (orderDate != null ? !orderDate.equals(orders.orderDate) : orders.orderDate != null) return false;
        if (state != null ? !state.equals(orders.state) : orders.state != null) return false;
      //  if (waiter != null ? !waiter.equals(orders.waiter) : orders.waiter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;// = dishes != null ? dishes.hashCode() : 0;
        result = 31 * result + tableNumber;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
