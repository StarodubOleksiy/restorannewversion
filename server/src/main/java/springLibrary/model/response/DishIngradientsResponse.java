package springLibrary.model.response;


import lombok.Data;

@Data
public class DishIngradientsResponse implements Comparable<DishIngradientsResponse> {
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

    @Override
    public int compareTo(DishIngradientsResponse o) {
        int result = this.ingradientName.compareTo(o.ingradientName);
        return result;
    }

    public Long getDishId() {
        return dishId;
    }

    public Long getIngradientId() {
        return ingradientId;
    }

    public String getIngradientName() {
        return ingradientName;
    }

    public Integer getNumerosity() {
        return numerosity;
    }
}
