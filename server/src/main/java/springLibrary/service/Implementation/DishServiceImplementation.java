package springLibrary.service.Implementation;

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
import springLibrary.service.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Администратор on 23.02.18.
 */

@Service
public class DishServiceImplementation extends AbstractService<Dish, Long, DishRepository> implements DishService {


    @Autowired
    JdbcTemplate jdbcTemplate;

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
        return response;
    }


    @Override
    public List<DishResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::dishToDishResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveFromRequest(Dish dish, DishRequest dishRequest) {
        if (dishRequest.getImage() != null) //{
            dish.setImage(Base64.getDecoder().decode(dishRequest.getImage()));
        getRepository().save(dish);
    }


    @Override
    public Optional<DishResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::dishToDishResponse);
    }


}
