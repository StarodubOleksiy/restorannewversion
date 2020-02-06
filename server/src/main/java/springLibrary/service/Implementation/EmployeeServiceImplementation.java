package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springLibrary.entities.Employee;
import springLibrary.model.response.AuthorResponse;
import springLibrary.repository.EmployeeRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation extends AbstractService<Employee, Long, EmployeeRepository> implements EmployeeService {

    protected EmployeeServiceImplementation(@Autowired EmployeeRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImplementation.class);

    /*@Autowired
    DishService bookService;
*/
    @Autowired
    JdbcTemplate jdbcTemplate;

  /*  @Autowired
    IdOfAuthorsByBookRepository idOfAuthorsByBookRepository;

    private AuthorResponse authorToAuthorResponse(Author author) {
        AuthorResponse response = new AuthorResponse();
        response.setId(author.getId());
        response.setName(author.getFio());
        response.setBooksId(author.getBooks());
        return response;
    }
*/

    @Override
    public List<AuthorResponse> findAllResponse() {
        getRepository().findAll().forEach(System.out::println);
        return null;
    }


    @Override
    public Optional<AuthorResponse> findByIdResponse(Long id) {
        return null;
    }



}




