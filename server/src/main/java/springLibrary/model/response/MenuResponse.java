package springLibrary.model.response;

import lombok.Data;
import springLibrary.entities.Dish;
import springLibrary.entities.Menu;

@Data
public class MenuResponse {
    private Long id;
    private String name;


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static MenuResponse of(Menu menu) {
        MenuResponse response = new MenuResponse();
        response.setId(menu.getId());
        response.setName(menu.getName());
        return response;
    }
}
