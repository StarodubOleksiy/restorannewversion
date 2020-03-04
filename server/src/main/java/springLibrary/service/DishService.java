package springLibrary.service;

import springLibrary.entities.Dish;
import springLibrary.model.request.DishRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.repository.DishRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Администратор on 23.02.18.
 */

public interface DishService extends Service<Dish, Long, DishRepository> {

   public List<DishResponse> findAllResponse();

   public Optional<DishResponse> findByIdResponse(Long id);

   public void saveFromRequest(Dish dish, DishRequest dishRequest);

   public List<DishResponse> findDishesByName(String name);

}
