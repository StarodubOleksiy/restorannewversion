package springLibrary.service;

import springLibrary.entities.Ingradient;
import springLibrary.model.response.IngradientResponse;
import springLibrary.repository.IngradientRepository;

import java.util.List;
import java.util.Optional;

public interface IngradientService extends Service<Ingradient, Long, IngradientRepository> {

    public List<IngradientResponse> findAllResponse();

    public Optional<IngradientResponse> findByIdResponse(Long id);

}
