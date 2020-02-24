package springLibrary.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Cooked_Dish;
import springLibrary.entities.Dish;
import springLibrary.repository.CookedDishRepository;
import springLibrary.repository.DishRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.CookedDishService;
import springLibrary.service.DishService;

@Service
public class CookedDishServiceImplementation extends AbstractService<Cooked_Dish, Long, CookedDishRepository> implements CookedDishService
{
    protected CookedDishServiceImplementation(@Autowired CookedDishRepository repository) {
        super(repository);
    }

}
