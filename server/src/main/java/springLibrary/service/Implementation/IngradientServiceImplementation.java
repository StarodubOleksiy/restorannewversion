package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;
import springLibrary.model.response.IngradientResponse;
import springLibrary.repository.DishRepository;
import springLibrary.repository.IngradientRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.IngradientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class SomeClass
{
    public SomeClass()
    {

    }
}



@Service
public class IngradientServiceImplementation extends AbstractService<Ingradient, Long, IngradientRepository> implements IngradientService {

    @Autowired
    private DishRepository dishRepository;


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


    @Override //https://mkyong.com/spring/spring-jdbctemplate-querying-examples/
    public List<IngradientResponse> findIngradientsByDishIdResponse(Long id) {
        dishRepository.getOne(id).getIngradients().forEach(ingradient
                ->{
                    ingradient.getId();
                    SomeClass someClass = new SomeClass();
        }
        );

        return dishRepository.getOne(id).getIngradients().stream()
                .map(this::ingradientToIngradientResponse)
                .collect(Collectors.toList());
    }


    @Override
    public void save(Ingradient ingradient) {
        super.save(ingradient);

    }


}
