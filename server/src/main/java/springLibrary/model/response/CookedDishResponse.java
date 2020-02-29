package springLibrary.model.response;

import lombok.Data;
import springLibrary.entities.Cook;
import springLibrary.entities.Dish;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class CookedDishResponse {

    private Long id;
    private Long dishId;
    private String dishName;
    private float dishPrice;
    private Long cookerId;
    private String cookerName;
    private String cookerSurname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public float getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(float dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getCookerName() {
        return cookerName;
    }

    public void setCookerName(String cookerName) {
        this.cookerName = cookerName;
    }

    public String getCookerSurname() {
        return cookerSurname;
    }

    public void setCookerSurname(String cookerSurname) {
        this.cookerSurname = cookerSurname;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getCookerId() {
        return cookerId;
    }

    public void setCookerId(Long cookerId) {
        this.cookerId = cookerId;
    }
}
