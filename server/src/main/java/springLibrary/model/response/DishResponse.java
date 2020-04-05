package springLibrary.model.response;


import lombok.Data;
import springLibrary.entities.Dish;
import springLibrary.entities.Ingradient;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Base64;
import java.util.List;

@Data
public class DishResponse implements Comparable<DishResponse>{
    private Long id;
    private String name;
    private float price;
    private float weight;
    private int menuId;
    private String menuName;
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setImage(byte[] image) {
        this.image = Base64.getEncoder().encodeToString(image);
    }

    public String getImage() {
        return image;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public static DishResponse of(Dish dish) {
        DishResponse response = new DishResponse();
        response.setId(dish.getId());
        response.setName(dish.getName());
        if (dish.getImage() != null) {
            response.setImage(dish.getImage());
        }
        response.setPrice(dish.getPrice());
        response.setWeight(dish.getWeight());
        response.setMenuId(dish.getMenu().getId().intValue());
        return response;
    }

    @Override
    public int compareTo(DishResponse o) {
        int result = this.name.compareTo(o.name);
        return result;
    }

}
