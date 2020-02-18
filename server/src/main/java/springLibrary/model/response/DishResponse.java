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
public class DishResponse {
    private Long id;
    private String name;
    private float price;
    private float weight;
    private int menuId;
    private String image;
    private Integer[] ingradientsId;

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

    public void setIngradientsId(List<Ingradient> ingradients) {
        this.ingradientsId = new Integer[ingradients.size()];
        int index = 0;
        for (int i = 0; i < ingradients.size(); ++i)
            ingradientsId[index++] = (int) ingradients.get(i).getId().longValue();
       /* for (Iterator<Author> it = authors.iterator(); it.hasNext(); ) {
            Author author = it.next();
            authorsId[index++] = (int) author.getId().longValue();
        }*/
    }
}