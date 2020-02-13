package springLibrary.model.request;

import lombok.Data;
import springLibrary.entities.Dish;


@Data
public class DishRequest {
    private Long id;
    private String name;
    //private String image;
    private Float price;
    private Float weight;
    private Integer[] ingradientsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Dish toDish() {
        Dish dish = new Dish();
        if (id != null)
            dish.setId(id);
        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        return dish;
    }



}
