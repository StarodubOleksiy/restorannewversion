package springLibrary.service;

import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;
import springLibrary.model.request.DishIngradientRequest;
import springLibrary.model.response.DishIngradientsResponse;
import springLibrary.model.response.IngradientResponse;
import springLibrary.repository.IngradientRepository;

import java.util.List;
import java.util.Optional;

public interface IngradientService extends Service<Ingradient, Long, IngradientRepository> {

    public List<IngradientResponse> findAllResponse();

    public Optional<IngradientResponse> findByIdResponse(Long id);

    public void save(Ingradient ingradient);

    public List<DishIngradientsResponse> findIngradientsByDishIdResponse(Long dish_id);

    public List<IngradientResponse> findNewIngradientsResponse(Long id);

    public void addIngradientToDish(DishIngradientRequest dishIngradientRequest);

}
