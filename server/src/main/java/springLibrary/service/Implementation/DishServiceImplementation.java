package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springLibrary.entities.Dish;
import springLibrary.model.response.BookResponse;
import springLibrary.repository.DishRepository;
import springLibrary.service.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Администратор on 23.02.18.
 */

@Service
public class DishServiceImplementation extends AbstractService<Dish, Long, DishRepository> implements DishService {


    @Autowired
    JdbcTemplate jdbcTemplate;


    protected DishServiceImplementation(@Autowired DishRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(DishServiceImplementation.class);


   /* private BookResponse bookToBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setId(book.getId());
        response.setName(book.getName());
        if (book.getImage() != null)
            response.setImage(book.getImage());
        response.setIsbn(book.getIsbn());
        response.setPublishYear(book.getPublishYear());
        response.setDescr(book.getDescr());
        response.setRoom(book.getRoom());
        response.setGenreId(book.getGenre().getId());
        response.setPublisherId(book.getPublisher().getId());
        response.setPageCount(book.getPageCount());
        response.setPlacing(book.getPlacing());
        response.setType(book.getType());
        response.setAuthorsId(book.getAuthors());
        return response;
    }
*/



    @Override
    public List<BookResponse> findAllResponse() {
        getRepository().findAll().forEach(System.out::println);
        return null;
    }


    /*@Override
    public List<BookResponse> findByCharacterResponse(String character) {
        return null;
    }

    @Override
    public List<BookResponse> findByTitleResponse(String title) {
        return null;
    }*/


    @Override
    public Optional<BookResponse> findByIdResponse(Long id) {
        return null;
    }






}
