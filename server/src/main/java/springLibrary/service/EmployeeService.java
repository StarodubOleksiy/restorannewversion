package springLibrary.service;

import springLibrary.entities.Dish;
import springLibrary.entities.Employee;
import springLibrary.model.request.DishRequest;
import springLibrary.model.request.EmployeeRequest;
import springLibrary.model.response.EmployeeResponse;
import springLibrary.repository.EmployeeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService extends Service<Employee, Long, EmployeeRepository> {


    public List<EmployeeResponse> findAllResponse();

    public Optional<EmployeeResponse> findByIdResponse(Long id);

    public List<EmployeeResponse> getAllWaiters();

    public void saveFromRequest(Employee employee, EmployeeRequest employeeRequest);

}
