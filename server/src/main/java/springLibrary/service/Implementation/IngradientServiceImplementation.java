package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Ingradient;
import springLibrary.model.response.PublisherResponse;
import springLibrary.repository.IngradientRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.IngradientService;

import java.util.List;
import java.util.Optional;


@Service
public class IngradientServiceImplementation extends AbstractService<Ingradient, Long, IngradientRepository> implements IngradientService {

    protected IngradientServiceImplementation(@Autowired IngradientRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(IngradientServiceImplementation.class);

   /* private PublisherResponse publisherToPublisherResponse(Publisher publisher) {
        PublisherResponse response = new PublisherResponse();
        response.setId(publisher.getId());
        response.setName(publisher.getName());
        response.setCity(publisher.getCity());
        return response;
    }
*/


    @Override
    public Optional<PublisherResponse> findByIdResponse(Long id) {
        return null;
    }


    @Override
    public List<PublisherResponse> findAllResponse() {
        System.out.println("Ingradients getRepository().findAll().size() = "+getRepository().findAll().size());
        getRepository().findAll().forEach(System.out::println);
        return null;  }


}
