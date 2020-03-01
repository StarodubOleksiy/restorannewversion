package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;
import springLibrary.model.request.DishIngradientRequest;
import springLibrary.model.response.DishIngradientsResponse;
import springLibrary.model.response.IngradientResponse;
import springLibrary.repository.DishRepository;
import springLibrary.repository.IngradientRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.IngradientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Service
public class IngradientServiceImplementation extends AbstractService<Ingradient, Long, IngradientRepository> implements IngradientService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private IngradientRepository ingradientRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    protected IngradientServiceImplementation(@Autowired IngradientRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(IngradientServiceImplementation.class);

    private IngradientResponse ingradientToIngradientResponse(Ingradient ingradient) {
        IngradientResponse response = new IngradientResponse();
        response.setId(ingradient.getId());
        response.setName(ingradient.getName());
        response.setNumberOnStorage(ingradient.getNumerosity());
        return response;
    }


    @Override
    public Optional<IngradientResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::ingradientToIngradientResponse);
    }


    @Override
    public List<IngradientResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::ingradientToIngradientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<IngradientResponse> findNewIngradientsResponse(Long id)
    {
        List<IngradientResponse> newIngradients = new ArrayList<>();
        getRepository().findAll().forEach(ingradient -> {
            if(!dishRepository.getOne(id).getIngradients().contains(ingradient))
                newIngradients.add(ingradientToIngradientResponse(ingradient));
        });
        return newIngradients;
    }


    @Override
    public Optional<DishIngradientsResponse> getCurrentIngradientInDish(Long dish_id, Long ingradient_id)
    {
        String sql = "SELECT numerosity FROM dish_to_ingradient WHERE dish_id = ? and ingradient_id = ?";
        Optional<DishIngradientsResponse> optionalIngradientResponse = Optional.of(
                new DishIngradientsResponse(dish_id, ingradient_id,
                ingradientRepository.getOne(ingradient_id).getName(),jdbcTemplate.queryForObject(
                sql, new Object[]{dish_id, ingradient_id}, Integer.class)));
        return optionalIngradientResponse;
    }



    @Override //mkyong.com/spring/spring-jdbctemplate-querying-examples/
    public List<DishIngradientsResponse> findIngradientsByDishIdResponse(Long dish_id) {
        List <DishIngradientsResponse> listDishIngradientsResponse = new ArrayList<>();
        String sql = "SELECT numerosity FROM dish_to_ingradient WHERE dish_id = ? and ingradient_id = ?";
        dishRepository.getOne(dish_id).getIngradients().forEach(
                ingradient
                        -> {
                    listDishIngradientsResponse.add(new DishIngradientsResponse(dish_id,ingradient.getId(),
                            ingradient.getName(),jdbcTemplate.queryForObject(
                            sql, new Object[]{dish_id, ingradient.getId()}, Integer.class)));
                }
        );
        return listDishIngradientsResponse;
    }


    @Override
    @Transactional
    public void addIngradientToDish(DishIngradientRequest dishIngradientRequest) {
        jdbcTemplate.update("INSERT INTO dish_to_ingradient (dish_id, ingradient_id, numerosity) VALUES (?, ?, ?)",
                dishIngradientRequest.getDishId(),dishIngradientRequest.getIngradientId(),dishIngradientRequest.getNumerosity());

    }

    @Override
    @Transactional
    public void changeNumerosityOfIngradientsInDish(DishIngradientRequest dishIngradientRequest) {
        jdbcTemplate.update("UPDATE dish_to_ingradient SET numerosity = ? WHERE dish_id = ? and ingradient_id = ?",
                dishIngradientRequest.getNumerosity(),dishIngradientRequest.getDishId(),dishIngradientRequest.getIngradientId());

    }

    @Override
    @Transactional
    public void deleteIngradientFromCurrentDish(DishIngradientRequest dishIngradientRequest) {
        jdbcTemplate.update("DELETE FROM dish_to_ingradient WHERE dish_id = ? and ingradient_id = ?",
               dishIngradientRequest.getDishId(),dishIngradientRequest.getIngradientId());

    }

    @Override
    @Transactional
    public void deleteAllIngradientsFromCurrentDish(Long dishId) {
        jdbcTemplate.update("DELETE FROM dish_to_ingradient WHERE dish_id = ?",
                dishId);

    }





    @Override
    public void save(Ingradient ingradient) {
        super.save(ingradient);

    }


}
