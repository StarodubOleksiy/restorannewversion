package springLibrary.model.response;


import lombok.Data;
import springLibrary.entities.Ingradient;
import springLibrary.entities.Orders;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderResponse {
    private Long id;
    private int tableNumber;
    private String orderDate;
    private String waiterName;
    private String waiterSurname;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getWaiterSurname() {
        return waiterSurname;
    }

    public void setWaiterSurname(String waiterSurname) {
        this.waiterSurname = waiterSurname;
    }

    public static OrderResponse of(Orders order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setTableNumber(order.getTableNumber());
        response.setOrderDate(order.getOrderDate());
        response.setState(order.getState());
        return response;
    }


}
