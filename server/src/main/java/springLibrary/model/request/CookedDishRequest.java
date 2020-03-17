package springLibrary.model.request;


import lombok.Data;

@Data
public class CookedDishRequest {

    private Long id;
    private Long dishId;
    private Long cookerId;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long oederId) {
        this.orderId = oederId;
    }

    @Override
    public String toString() {
        return "CookedDishRequest{" +
                "id=" + id +
                ", dishId=" + dishId +
                ", cookerId=" + cookerId +
                ", orderId=" + orderId +
                '}';
    }
}
