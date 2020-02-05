package springLibrary.service;

import springLibrary.model.response.PublisherResponse;
import springLibrary.repository.PublisherRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherService extends Service<Publisher, Long, PublisherRepository> {

    public List<PublisherResponse> findAllResponse();

    public Optional<PublisherResponse> findByIdResponse(Long id);

    public void save(Publisher publisher);

    public List<Publisher> findByName(String name);

    public List<PublisherResponse> findByNameResponse(String title);

    public List<Publisher> findByCity(String name);

    public List<PublisherResponse> findByCityResponse(String city);

    public List<PublisherResponse> findByCharacterResponse(String character);

}
