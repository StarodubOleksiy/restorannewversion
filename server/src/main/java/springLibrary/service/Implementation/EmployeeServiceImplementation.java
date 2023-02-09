package springLibrary.service.Implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import springLibrary.entities.Cook;
import springLibrary.entities.Employee;
import springLibrary.mapper.EmployeeMapper;
import springLibrary.model.request.EmployeeRequest;
import springLibrary.model.response.EmployeeResponse;
import springLibrary.repository.EmployeeRepository;
import springLibrary.service.AbstractService;
import springLibrary.service.EmployeeService;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImplementation extends AbstractService<Employee, Long, EmployeeRepository> implements EmployeeService {

    protected EmployeeServiceImplementation(@Autowired EmployeeRepository repository) {
        super(repository);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImplementation.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_GET_ALL_WAITERS = "SELECT * FROM employee  where dtype = 'Waiter' order BY surname";

    private final String SQL_GET_ALL_COOKERS = "SELECT * FROM employee  where dtype = 'Cook' order BY surname";


    private EmployeeResponse employeeToEmployeeResponse(Employee employee) {
        LOGGER.info("employee.getClass().getCanonicalName() = " + employee.getClass().getCanonicalName());
        LOGGER.info("employee.getClass().getName() = " + employee.getClass().getName());
        LOGGER.info("employee.getClass().getName().getSimpleName() = " + employee.getClass().getSimpleName());
        LOGGER.info("employee.getClass().getName().getTypeName() = " + employee.getClass().getTypeName());
        if (employee instanceof Cook)
            LOGGER.info("instance of cook");
        else
            LOGGER.info("instance of waiter");
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        if (employee.getPhotography() != null) {
            response.setPhotography(employee.getPhotography());
        }
        response.setSurname(employee.getSurname());
        response.setPhoneNumber(employee.getPhoneNumber());
        if (employee.getClass().getSimpleName().equals("Cook"))
            response.setPosition("COOK");
        else
            response.setPosition("WAITER");
        response.setSalary(employee.getSalary());
        return response;
    }

    @Override
    public List<EmployeeResponse> findAllResponse() {
        LOGGER.info("");
        return getRepository().findAll().stream()
                .map(this::employeeToEmployeeResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<EmployeeResponse> getAllWaiters() {
        return jdbcTemplate.query(SQL_GET_ALL_WAITERS, new EmployeeMapper())
                .stream().map(this::employeeToEmployeeResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<EmployeeResponse> getAllCookers() {
        return jdbcTemplate.query(SQL_GET_ALL_COOKERS, new EmployeeMapper())
                .stream().map(this::employeeToEmployeeResponse)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<EmployeeResponse> findByIdResponse(Long id) {
        return getRepository().findById(id).map(this::employeeToEmployeeResponse);
    }

    @Override
    @Transactional
    public void saveFromRequest(EmployeeRequest employeeRequest) {
        Employee employee = employeeRequest.toEmployee();
        getRepository().save(employee);
    }

    @Override
    @Transactional
    public void updateFromRequest(EmployeeRequest employeeRequest) {
        Employee employee = getOne(employeeRequest.getId());
        employee.setName(employeeRequest.getName());
        employee.setSurname(employeeRequest.getSurname());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setSalary(employeeRequest.getSalary());
        if (employeeRequest.getPhotography() != null)
            employee.setPhotography(Base64.getDecoder().decode(employeeRequest.getPhotography()));
        getRepository().save(employee);
    }

}




