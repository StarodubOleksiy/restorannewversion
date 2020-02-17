package springLibrary.model.request;

import lombok.Data;
import springLibrary.entities.Menu;


@Data
public class MenuRequest {
    private Long id;
    private String name;

    public Menu toMenu() {
        Menu menu = new Menu();
        if (id != null)
            menu.setId(id);
        menu.setName(name);
        return menu;
    }

    @Override
    public String toString() {
        return "MenuRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
