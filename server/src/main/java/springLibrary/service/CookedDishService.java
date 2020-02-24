package springLibrary.service;

import springLibrary.entities.Cooked_Dish;
import springLibrary.entities.Dish;
import springLibrary.repository.CookedDishRepository;
import springLibrary.repository.DishRepository;

public interface CookedDishService extends Service<Cooked_Dish, Long, CookedDishRepository>  {

}
