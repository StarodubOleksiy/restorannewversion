package springLibrary.model.request;

import lombok.Data;
import springLibrary.entities.Menu;


@Data
public class MenuRequest {
    private Long id;
    private String name;

    public Menu toMenu() {
        Menu menu = new Menu();
        menu.setName(name);
        return menu;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MenuRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
