package springLibrary.service;

import springLibrary.entities.Ingradient;
import springLibrary.model.response.PublisherResponse;
import springLibrary.repository.IngradientRepository;

import java.util.List;
import java.util.Optional;

public interface IngradientService extends Service<Ingradient, Long, IngradientRepository> {

    public List<PublisherResponse> findAllResponse();

    public Optional<PublisherResponse> findByIdResponse(Long id);

}
