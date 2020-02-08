package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import springLibrary.entities.Employee;
import springLibrary.enums.Position;
import springLibrary.model.response.EmployeeResponse;
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


    private EmployeeResponse employeeToEmployeeResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
       /* if (book.getImage() != null) {
            response.setImage(book.getImage());
        }*/
        response.setSurname(employee.getSurname());
        response.setPhoneNumber(employee.getPhoneNumber());
        response.setPosition(Position.enumToString(employee.getPosition()));
        response.setSalary(employee.getSalary());
        return response;
    }

    @Override
    public List<EmployeeResponse> findAllResponse() {
        return getRepository().findAll().stream()
                .map(this::employeeToEmployeeResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<EmployeeResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::employeeToEmployeeResponse);
    }

}




