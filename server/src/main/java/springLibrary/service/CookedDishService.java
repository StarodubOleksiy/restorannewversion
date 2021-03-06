package springLibrary.service;

import springLibrary.entities.Cooked_Dish;
import springLibrary.entities.Dish;
import springLibrary.model.request.CookedDishRequest;
import springLibrary.model.response.CookedDishResponse;
import springLibrary.repository.CookedDishRepository;
import springLibrary.repository.DishRepository;

import java.util.List;
import java.util.Optional;

public interface CookedDishService extends Service<Cooked_Dish, Long, CookedDishRepository>  {

    public List< CookedDishResponse> findAllResponse();

    public Optional< CookedDishResponse> findByIdResponse(Long id);

    public List< CookedDishResponse> findByOrderIdResponse(Long orderId);

    public void saveCookedDish(CookedDishRequest cookedDishRequest);

}
