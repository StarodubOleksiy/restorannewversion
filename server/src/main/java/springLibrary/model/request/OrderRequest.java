package springLibrary.model.request;

import lombok.Data;
import springLibrary.entities.Menu;
import springLibrary.entities.Orders;

@Data
public class OrderRequest {
    private Long id;
    private Long waiterId;
    private String tableNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Orders toOrder() {
        Orders order = new Orders();
        if (id != null)
            order.setId(id);
        order.setTableNumber(Integer.valueOf(tableNumber));
        return order;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", waiterId=" + waiterId +
                ", tableNumber='" + tableNumber + '\'' +
                '}';
    }
}
