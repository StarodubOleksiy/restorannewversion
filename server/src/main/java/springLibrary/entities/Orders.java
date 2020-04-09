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
public class Orders extends AbstractIdentifiableEntity {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Waiter waiter;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Cooked_Dish> dishes;

    @NotNull
    @Column(name = "table_number", nullable = false)
    private int tableNumber;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @NotNull
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus state;
    //private String state;

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

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

    public void setState(OrderStatus state) {
        this.state = state;
    }

    public void setStateClose() {
        this.state = OrderStatus.close;
    }

    public OrderStatus getState() {
        return state;
    }

    public Set<Cooked_Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Cooked_Dish> dishes) {
        this.dishes = dishes;
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
        if (waiter != null ? !waiter.equals(orders.waiter) : orders.waiter != null) return false;

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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + super.getId() +
                "tableNumber=" + tableNumber +
                ", orderDate='" + orderDate + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
