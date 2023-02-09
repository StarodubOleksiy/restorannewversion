package springLibrary.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springLibrary.entities.Cook;
import springLibrary.entities.Cooked_Dish;
import springLibrary.model.request.CookedDishRequest;
import springLibrary.model.response.CookedDishResponse;
import springLibrary.repository.CookedDishRepository;
import springLibrary.repository.DishRepository;
import springLibrary.repository.EmployeeRepository;
import springLibrary.repository.OrderRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.CookedDishService;
import springLibrary.service.EmployeeService;

import javax.transaction.Transactional;
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

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SQL_DELETE_COOKED_DISH = "DELETE FROM cooked_dish WHERE id=%d";

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
    @Transactional
    public List<CookedDishResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::cookedDishToCookedDishResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public List<CookedDishResponse> findByOrderIdResponse(Long orderId) {
        return orderRepository.getOne(orderId).getDishes().stream()
                .map(this::cookedDishToCookedDishResponse)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
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


    @Override
    @Transactional
    public void deleteCookedDish(Long id) {
       // getRepository().deleteById(id); //Todo this method does not work and I do not know why.
        jdbcTemplate.execute(String.format(SQL_DELETE_COOKED_DISH,id));
    }

    @Override
    @Transactional
    public void saveFromRequest(CookedDishRequest cookedDishRequest)
    {
        Cooked_Dish cookedDish = new Cooked_Dish();
        cookedDish.setCook(new Cook(employeeRepository.getOne(cookedDishRequest.getCookerId())));
        cookedDish.setOrder(orderRepository.getOne(cookedDishRequest.getOrderId()));
        cookedDish.setDish(dishRepository.getOne(cookedDishRequest.getDishId()));
        getRepository().save(cookedDish);
    }

    @Override
    @Transactional
    public void updateFromRequest(CookedDishRequest cookedDishRequest)
    {
        Cooked_Dish cookedDish = getOne(cookedDishRequest.getId());
        cookedDish.setCook(new Cook(employeeRepository.getOne(cookedDishRequest.getCookerId())));
        getRepository().save(cookedDish);
    }


}
