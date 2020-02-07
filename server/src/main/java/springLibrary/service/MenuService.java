package springLibrary.service;

import springLibrary.entities.Dish;
import springLibrary.entities.Menu;
import springLibrary.model.response.BookResponse;
import springLibrary.repository.DishRepository;
import springLibrary.repository.MenuRepository;

import java.util.List;
import java.util.Optional;

public interface MenuService extends Service<Menu, Long, MenuRepository> {

    public List<BookResponse> findAllResponse();

    public Optional<BookResponse> findByIdResponse(Long id);
}
