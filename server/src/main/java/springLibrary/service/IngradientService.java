package springLibrary.service;

import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;
import springLibrary.model.request.DishIngradientRequest;
import springLibrary.model.request.IngradientRequest;
import springLibrary.model.response.DishIngradientsResponse;
import springLibrary.model.response.IngradientResponse;
import springLibrary.repository.IngradientRepository;

import java.util.List;
import java.util.Optional;

public interface IngradientService extends Service<Ingradient, Long, IngradientRepository> {

    public List<IngradientResponse> findAllResponse();

    public Optional<IngradientResponse> findByIdResponse(Long id);

    public List<DishIngradientsResponse> findIngradientsByDishIdResponse(Long dish_id);

    public List<IngradientResponse> findNewIngradientsResponse(Long id);

    public void addIngradientToDish(DishIngradientRequest dishIngradientRequest);

    public Optional<DishIngradientsResponse> getCurrentIngradientInDish(Long dish_id, Long ingradient_id);

    public void changeNumerosityOfIngradientsInDish(DishIngradientRequest dishIngradientRequest);

    public void deleteIngradientFromCurrentDish(DishIngradientRequest dishIngradientRequest);

    public void deleteAllIngradientsFromCurrentDish(Long dishId);

    public List<IngradientResponse> findIngradientsByName(String name);

    public void saveFromRequest(IngradientRequest ingradientRequest);

    public void updateFromRequest(IngradientRequest ingradientRequest);

}
