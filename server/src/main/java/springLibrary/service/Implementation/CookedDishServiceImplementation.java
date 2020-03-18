package springLibrary.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Cook;
import springLibrary.entities.Cooked_Dish;
import springLibrary.entities.Dish;
import springLibrary.model.request.CookedDishRequest;
import springLibrary.model.request.DishRequest;
import springLibrary.model.response.CookedDishResponse;
import springLibrary.model.response.DishResponse;
import springLibrary.repository.CookedDishRepository;
import springLibrary.repository.DishRepository;
import springLibrary.repository.EmployeeRepository;
import springLibrary.repository.OrderRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.CookedDishService;
import springLibrary.service.DishService;

import javax.transaction.Transactional;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CookedDishServiceImplementation extends AbstractService<Cooked_Dish, Long, CookedDishRepository> implements CookedDishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    protected CookedDishServiceImplementation(@Autowired CookedDishRepository repository) {
        super(repository);
    }

    private CookedDishResponse cookedDishToCookedDishResponse(Cooked_Dish cookedDish) {
        CookedDishResponse response = new CookedDishResponse();
        response.setId(cookedDish.getId());
        response.setDishId(cookedDish.getDish().getId());
        response.setDishName(cookedDish.getDish().getName());
        response.setDishPrice(cookedDish.getDish().getPrice());
        response.setCookerId(cookedDish.getCook().getId());//
        response.setCookerName(cookedDish.getCook().getName());
        response.setCookerSurname(cookedDish.getCook().getSurname());
        return response;
    }


    @Override
    public List<CookedDishResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::cookedDishToCookedDishResponse)
                .collect(Collectors.toList());
    }


    @Override
    public List<CookedDishResponse> findByOrderIdResponse(Long orderId) {
        return orderRepository.getOne(orderId).getDishes().stream()
                .map(this::cookedDishToCookedDishResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<CookedDishResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::cookedDishToCookedDishResponse);
    }


    @Override
    @Transactional
    public void saveCookedDish(CookedDishRequest cookedDishRequest) {
        Cooked_Dish cookedDish = new Cooked_Dish();
        if (cookedDishRequest.getId() != null)
            cookedDish.setId(cookedDishRequest.getId());
        cookedDish.setCook(new Cook(employeeRepository.getOne(cookedDishRequest.getCookerId())));
        cookedDish.setOrder(orderRepository.getOne(cookedDishRequest.getOrderId()));
        cookedDish.setDish(dishRepository.getOne(cookedDishRequest.getDishId()));
        getRepository().save(cookedDish);
    }


}
