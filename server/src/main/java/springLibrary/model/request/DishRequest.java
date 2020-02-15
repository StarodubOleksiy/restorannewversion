package springLibrary.model.request;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springLibrary.controller.DishController;
import springLibrary.entities.Dish;
import springLibrary.service.MenuService;


@Data
public class DishRequest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DishRequest.class);
    private Long id;
    private String name;
    private String image;
    private Float price;
    private Float weight;
    private int menuId;
    private Integer[] ingradientsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
