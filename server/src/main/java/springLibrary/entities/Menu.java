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

  /*  public Menu() {
        this.dishes = new ArrayList<Dish>();
    }*/

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

   /* @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_to_menu",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (!dishes.equals(menu.dishes)) return false;
        if (!name.equals(menu.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dishes.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Menu{" +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }

*/
}
