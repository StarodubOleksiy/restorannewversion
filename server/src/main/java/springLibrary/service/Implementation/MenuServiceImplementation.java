package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Ingradient;
import springLibrary.entities.Menu;
import springLibrary.model.response.BookResponse;
import springLibrary.model.response.GenreResponse;
import springLibrary.repository.IngradientRepository;
import springLibrary.repository.MenuRepository;
import springLibrary.repository.OrderRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.IngradientService;
import springLibrary.service.MenuService;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImplementation extends AbstractService<Menu, Long, MenuRepository> implements MenuService {

    protected MenuServiceImplementation(@Autowired MenuRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImplementation.class);


    /*private GenreResponse genreToGenreResponse(Genre genre) {
        GenreResponse response = new GenreResponse();
        response.setId(genre.getId());
        response.setName(genre.getName());
        return response;
    }*/


    @Override
    public List<BookResponse> findAllResponse() {
        getRepository().findAll().forEach(System.out::println);
        return null;
    }



    @Override
    public Optional<BookResponse> findByIdResponse(Long id) {
        return null;
    }


}

