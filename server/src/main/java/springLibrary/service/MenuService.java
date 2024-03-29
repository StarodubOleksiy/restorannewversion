package springLibrary.service;

import springLibrary.entities.Menu;
import springLibrary.model.request.MenuRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.model.response.MenuResponse;
import springLibrary.repository.MenuRepository;

import java.util.List;
import java.util.Optional;

public interface MenuService extends Service<Menu, Long, MenuRepository> {

    public List<MenuResponse> findAllResponse();

    public Optional<MenuResponse> findByIdResponse(Long id);

    public void saveFromRequest(MenuRequest menuRequest);

    public void updateFromRequest(MenuRequest menuRequest);

}
