package springLibrary.model.response;


import lombok.Data;

@Data
public class DishIngradientsResponse {
    private Long dishId;
    private Long ingradientId;
    private String ingradientName;
    private Integer numerosity;

    public DishIngradientsResponse(Long dishId, Long ingradientId, String ingradientName, Integer numerosity) {
        this.dishId = dishId;
        this.ingradientId = ingradientId;
        this.ingradientName = ingradientName;
        this.numerosity = numerosity;
    }
}
