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

    public void setOrder(Orders order) {
        this.order = order;
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

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "CookedDishes{" +
                ", dish=" + dish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cooked_Dish that = (Cooked_Dish) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
       return getId().intValue();
    }
}
