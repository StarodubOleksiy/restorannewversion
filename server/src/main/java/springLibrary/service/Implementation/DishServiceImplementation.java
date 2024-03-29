package springLibrary.service.Implementation;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import springLibrary.entities.Dish;
import springLibrary.model.request.DishRequest;
import springLibrary.model.response.DishResponse;
import springLibrary.repository.DishRepository;
import springLibrary.repository.MenuRepository;
import springLibrary.service.*;

import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Администратор on 23.02.18.
 */

@Service
public class DishServiceImplementation extends AbstractService<Dish, Long, DishRepository> implements DishService {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    IngradientService ingradientService;

    @Autowired
    private MenuService menuService;


    protected DishServiceImplementation(@Autowired DishRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DishServiceImplementation.class);


    private DishResponse dishToDishResponse(Dish dish) {
        DishResponse response = new DishResponse();
        response.setId(dish.getId());
        response.setName(dish.getName());
        if (dish.getImage() != null) {
            response.setImage(dish.getImage());
        }
        response.setPrice(dish.getPrice());
        response.setWeight(dish.getWeight());
        response.setMenuId(dish.getMenu().getId().intValue());
        response.setMenuName(dish.getMenu().getName());
        return response;
    }

    private void assignDish(Dish dish,DishRequest dishRequest)
    {
        dish.setMenu(menuService.findById(Long.valueOf(dishRequest.getMenuId())).orElse(null));
    }


    @Override
    public List<DishResponse> findDishesByName(String name) {
        return getRepository().findByName(name).stream()
                .map(this::dishToDishResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DishResponse> findDishesByMenu(Long id) {
        return menuRepository.getOne(id).getDishes().stream()
                .map(this::dishToDishResponse)
                   .collect(Collectors.toList());
    }


    @Override
    public List<DishResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::dishToDishResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveFromRequest(DishRequest dishRequest)  {
        Dish dish = dishRequest.toDish();
        this.assignDish(dish,dishRequest);
        getRepository().save(dish);
    }

    @Override
    @Transactional
    public void updateFromRequest(DishRequest dishRequest)
    {
        Dish dish = getOne(dishRequest.getId());
        dishRequest.setDishFromRequest(dish);
        this.assignDish(dish,dishRequest);
        getRepository().save(dish);
    }


    @Override
    public Optional<DishResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::dishToDishResponse);
    }

    @Override
    public void deleteDish(Long id) {
        ingradientService.deleteAllIngradientsFromCurrentDish(id);
        getRepository().deleteById(id);
    }


}
