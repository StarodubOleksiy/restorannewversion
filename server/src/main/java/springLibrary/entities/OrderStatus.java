package springLibrary.entities;

public enum OrderStatus {
    OPEN("OPEN"),
    CLOSE("CLOSE");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public static OrderStatus stringToEnum(String status) {
        switch (status) {
            case "OPEN":
                return OPEN;
                 default:
                return CLOSE;
        }
    }

    public static String enumToString(OrderStatus orderStatus) {
        return orderStatus.getStatus();
    }

    }
