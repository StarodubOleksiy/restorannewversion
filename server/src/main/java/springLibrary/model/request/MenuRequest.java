package springLibrary.model.request;

import lombok.Data;
import springLibrary.entities.Menu;


@Data
public class MenuRequest {
    private Long id;
    private String name;

    public Menu toMenu() {
        Menu menu = new Menu();
        this.setMenuFromRequest(menu);
        return menu;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setMenuFromRequest(Menu menu) {
        menu.setName(name);
    }

    @Override
    public String toString() {
        return "MenuRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
