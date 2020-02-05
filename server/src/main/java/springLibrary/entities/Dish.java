package springLibrary.entities;

import lombok.Getter;
import lombok.Setter;
import springLibrary.enums.DishCategory;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Администратор on 09.06.16.
 */
//////
@Entity
@Table(name = "dish")
@Getter
@Setter
public class Dish extends AbstractIdentifiableEntity {

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;


    @NotNull
    @Column(name = "dish_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private DishCategory dishCategory;//

    @Min(0)
    @NotNull
    @Column(name = "price", nullable = false)
    private float price;

    @Min(0)
    @NotNull
    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "image")
    private String image;


    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_to_ingradient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingradient_id")
    )

    private List<Storage> ingradients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishCategory getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(DishCategory dishCategory) {
        this.dishCategory = dishCategory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setIngradients(List<Storage> ingradients) {
        this.ingradients = ingradients;
    }

    public List<Storage> getIngradients() {
        return ingradients;
    }


    @Override
    public String toString() {
        return  name;  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        if (Float.compare(dish.price, price) != 0) return false;
        if (Float.compare(dish.weight, weight) != 0) return false;
        if (dishCategory != dish.dishCategory) return false;
        if (!name.equals(dish.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dishCategory.hashCode();
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        return result;
    }
}
