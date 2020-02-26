package springLibrary.model.request;

import lombok.Data;

@Data
public class DishIngradientRequest {

    private Long dishId;
    private Long  ingradientId;
    private int numerosity;

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getIngradientId() {
        return ingradientId;
    }

    public void setIngradientId(Long ingradientId) {
        this.ingradientId = ingradientId;
    }

    public int getNumerosity() {
        return numerosity;
    }

    public void setNumerosity(int numerosity) {
        this.numerosity = numerosity;
    }
}
