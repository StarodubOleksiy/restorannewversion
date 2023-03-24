package springLibrary.entities;

public enum OrderStatus {
    open("open"),
    close("close");


    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public static String enumToString(OrderStatus orderStatus) {
        return orderStatus.getStatus();
    }

}
