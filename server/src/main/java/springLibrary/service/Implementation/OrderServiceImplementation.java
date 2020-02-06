package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springLibrary.entities.Orders;
import springLibrary.model.response.GenreResponse;
import springLibrary.repository.OrderRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplementation extends AbstractService<Orders, Long, OrderRepository> implements OrderService {


    protected OrderServiceImplementation(@Autowired OrderRepository repository) {
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
    public List<GenreResponse> findAllResponse() {
        getRepository().findAll().forEach(System.out::println);
        return null;
    }



    @Override
    public Optional<GenreResponse> findByIdResponse(Long id) {
        return null;
    }


   /* public void save(Genre genre) {
        if(genre.getId() == 0)
            super.save(genre);
        else {
            if (getRepository().getOne(genre.getId()).getBooks() != null) ;
            genre.setBooks(getRepository().getOne(genre.getId()).getBooks());
            super.save(genre);
        }

    }*/


}
