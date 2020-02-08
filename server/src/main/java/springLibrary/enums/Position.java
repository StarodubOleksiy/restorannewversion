package springLibrary.enums;

/**
 * Created by Администратор on 08.06.16.
 */
public enum Position {

    WAITER("WAITER"),
    COOK("COOK"),
    MANAGER("MANAGER");

    private String name;

    Position(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Position stringToEnum(String position) {
        switch (position) {
            case "WAITER":
                return WAITER;
            case "COOK":
                return COOK;
            default:
                return MANAGER;
        }
    }

    public static String enumToString(Position position) {
        return position.getName();
    }
}
