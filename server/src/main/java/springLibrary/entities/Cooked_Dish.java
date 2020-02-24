package springLibrary.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Администратор on 05.07.16.
 */
@Entity
@Table(name = "cooked_dish")
@Getter
@Setter
public class Cooked_Dish extends AbstractIdentifiableEntity {

    @OneToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Cook  cook;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    /* public void setOrder(Orders order) {
        this.order = order;
    }

    public void setOrderDate(String orderDate) {
        this.date = orderDate;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }


    public Dish getDish() {
        return dish;
    }

    public Cook getCook() {
        return cook;
    }

    public Orders getOrder() {
        return order;
    }

    public String getOrderDate() {
        return date;
    }

    @Override
    public String toString() {
        return "CookedDishes{" +
                ", dish=" + dish +
                ", orderDate='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cooked_Dish that = (Cooked_Dish) o;

            if (cook != null ? !cook.equals(that.cook) : that.cook != null) return false;
        if (dish != null ? !dish.equals(that.dish) : that.dish != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + (cook != null ? cook.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }*/
}
