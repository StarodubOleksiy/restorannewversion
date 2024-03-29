package springLibrary.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

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


    @Min(0)
    @NotNull
    @Column(name = "price", nullable = false)
    private float price;

    @Min(0)
    @NotNull
    @Column(name = "weight", nullable = false)
    private float weight;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menuid")
    private Menu menu;

    @Column(name = "image")
    private byte[] image;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_to_ingradient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingradient_id")
    )
    private Set<Ingradient> ingradients;


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.capitalize(name.toLowerCase());
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

    public void setIngradients(Set<Ingradient> ingradients) {
        this.ingradients = ingradients;
    }

    public Set<Ingradient> getIngradients() {
        return ingradients;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        if (!name.toLowerCase().equals(dish.name.toLowerCase())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", image='" + image + '\'' +
                ", ingradients=" + ingradients +
                '}';
    }
}
