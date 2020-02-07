package springLibrary.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 20.08.16.
 */

@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu extends AbstractIdentifiableEntity {


    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Dish> dishes;

    public String getName() {
        return name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setName(String name) {
        this.name = name;
    }

   public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }


    @Override
    public String toString() {
        return "Menu{" +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }


}
